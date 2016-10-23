package com.infotoall.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class NewsPad extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    private Toolbar toolbarNewsPadActivity;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pad);

        toolbarNewsPadActivity=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbarNewsPadActivity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.add_box_black_icon);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setUpViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tabLayoutContainer);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        viewPager.setCurrentItem(1);// set open page as middle tab



    }

    private void setupTabIcons(){
        TextView tabOne=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tabOne.setText(getResources().getString(R.string.tab_one));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_library_books_black_24dp,0,0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tabTwo.setText(getResources().getString(R.string.home_string));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_home_black_24dp,0,0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tabThree.setText(getResources().getString(R.string.favorites));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_favorite_black_24dp,0,0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tabFour.setText(getResources().getString(R.string.recent_history));
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_save_black_24dp,0,0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TopicFragment(),"Your Articles");
        viewPagerAdapter.addFragment(new HomeFragment(),"Home");
        viewPagerAdapter.addFragment(new ActualSettingsFragment(),"Favorites");
        viewPagerAdapter.addFragment(new History(),"Recent History");
        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> stringList=new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            stringList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//for action bar
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.action_invite_friends);//for share action item of invite friends
        shareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);//for share action item of invite friends
        shareActionProvider.setShareIntent(setIntent(getResources().getString(R.string.invitation_message)));
        return true;
    }

    private Intent setIntent(String invitationMessage){ //providing message while invite friends in clicked
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,invitationMessage);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// for handling clicks in action bar
        switch (item.getItemId()){
            case R.id.action_actual_settings:
                Intent intent=new Intent(this,ActualSettings.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                Intent intent1=new Intent(this,SettingsActivity.class);
                SettingsActivity.intentMessage="useUp";
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
