using Android.App;
using Android.Widget;
using Android.OS;

namespace DamascusWay
{
    [Activity(Label = "DamascusWay", MainLauncher = true)]
    public class MainActivity : Activity
    {
        Button test;
        TextView textDisplay;
        int pressCounter = 0;


        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);

            test = FindViewById<Button>(Resource.Id.testButton);
            textDisplay = FindViewById<TextView>(Resource.Id.displayText);

            test.Click += Test_Click;

        }

        private void Test_Click(object sender, System.EventArgs e)
        {
            pressCounter++;
            textDisplay.Text = $@"Welcome to the mobile development team. This is the solution we will be using to develop the Android Application for Damascus Way
            You've pushed the button {pressCounter} times so far. Add some code and check it into source control so the other team members can see it.
            Remove text from here."

        }
    }
}

