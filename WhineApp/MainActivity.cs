using System;
using System.Collections.Generic;
using System.IO;
using Android.App;
using Android.Content;
using Android.Content.Res;
using Android.OS;
using Android.Preferences;
using Android.Runtime;
using Android.Support.Design.Widget;
using Android.Support.V7.App;
using Android.Views;
using Android.Widget;
using Java.Lang;
using Java.Util;
using Random = System.Random;

namespace WhineApp
{
    [Activity(Label = "@string/app_name", Theme = "@style/AppTheme.NoActionBar", MainLauncher = true)]
    public class MainActivity : AppCompatActivity
    {
        private EditText count_text;
        private int count;
        private ArrayList consequences;
        public static string MyPREFERENCES = "MyPrefs" ;
        ISharedPreferences history;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_main);

            Android.Support.V7.Widget.Toolbar toolbar = FindViewById<Android.Support.V7.Widget.Toolbar>(Resource.Id.toolbar);
            SetSupportActionBar(toolbar);

            count_text = FindViewById<EditText>(Resource.Id.count);
            count = 0;

            //current date, mm/dd/yyyy (no time) used for key
            DateTime date = DateTime.Now.Date;
            string str_date = date.ToString("d");

            history = PreferenceManager.GetDefaultSharedPreferences(this);

            // check if today has previous history and load if does
            if (history.Contains(str_date) == false)
            {
                ISharedPreferencesEditor editor = history.Edit();
                editor.PutInt(str_date, count);
                editor.Apply();
            } else
            {
                count = history.GetInt(str_date, 0);
            }

            string s = ""+count;
            count_text.Text = s;

            // get external list of consequences
            consequences = new ArrayList();
            using (StreamReader reader = new StreamReader(Assets.Open("consequences.txt")))
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    consequences.Add(line);
                }
            }

            FloatingActionButton fab = FindViewById<FloatingActionButton>(Resource.Id.fab);
            fab.Click += FabOnClick;
        }

        public override bool OnCreateOptionsMenu(IMenu menu)
        {
            MenuInflater.Inflate(Resource.Menu.menu_main, menu);
            return true;
        }

        public override bool OnOptionsItemSelected(IMenuItem item)
        {
            int id = item.ItemId;
            if (id == Resource.Id.action_settings)
            {
                return true;
            }

            return base.OnOptionsItemSelected(item);
        }

        private void FabOnClick(object sender, EventArgs eventArgs)
        {
            // Increment counts_of_day
            count++;
            count_text.Text = "" + count;

            //current date
            DateTime date = DateTime.Now.Date;
            string str_date = date.ToString("d");

            // save count internally
            ISharedPreferencesEditor editor = history.Edit();
            editor.PutInt(str_date, count);
            editor.Apply();

            // give consequence for each 5 whines
            if ((count % 5) == 0)
            {
                // randomize output
                Random rand = new Random();
                string string_consequence = consequences.Get(rand.Next(consequences.Size())).ToString();
                Console.WriteLine(string_consequence);
                View view = (View)sender;
                Snackbar.Make(view, string_consequence, Snackbar.LengthLong)
                    .SetAction("Action", (Android.Views.View.IOnClickListener)null).Show();
            }
        }

        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            Xamarin.Essentials.Platform.OnRequestPermissionsResult(requestCode, permissions, grantResults);

            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
	}
}
