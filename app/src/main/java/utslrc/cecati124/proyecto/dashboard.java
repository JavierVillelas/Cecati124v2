package utslrc.cecati124.proyecto;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import android.view.View;
import java.util.List;

public class dashboard extends AppCompatActivity {
    FragmentManager manager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.account);
        tabLayout.getTabAt(1).setIcon(R.drawable.account);
        tabLayout.getTabAt(2).setIcon(R.drawable.account);
        tabLayout.getTabAt(3).setIcon(R.drawable.account);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:{
                        tabLayout.getTabAt(0).setIcon(R.drawable.account);
                        tabLayout.getTabAt(1).setIcon(R.drawable.account);
                        tabLayout.getTabAt(2).setIcon(R.drawable.account);
                        tabLayout.getTabAt(3).setIcon(R.drawable.account);
                        break;
                    }
                    case 1:{
                        tabLayout.getTabAt(0).setIcon(R.drawable.accountcircle);
                        tabLayout.getTabAt(1).setIcon(R.drawable.account_plus);
                        tabLayout.getTabAt(2).setIcon(R.drawable.accountmultipleplus);
                        tabLayout.getTabAt(3).setIcon(R.drawable.calendarmultiple);
                        break;
                    }
                    case 2:{
                        tabLayout.getTabAt(0).setIcon(R.drawable.account);
                        tabLayout.getTabAt(1).setIcon(R.drawable.account);
                        tabLayout.getTabAt(2).setIcon(R.drawable.account);
                        tabLayout.getTabAt(3).setIcon(R.drawable.account);
                        break;
                    }
                    case 3:{
                        tabLayout.getTabAt(0).setIcon(R.drawable.account);
                        tabLayout.getTabAt(1).setIcon(R.drawable.account);
                        tabLayout.getTabAt(2).setIcon(R.drawable.account);
                        tabLayout.getTabAt(3).setIcon(R.drawable.account);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

      //  manager = getSupportFragmentManager();
     //   verEspecialidadTodoFragment ver = new verEspecialidadTodoFragment();
      //  manager.beginTransaction().replace(R.id.fragmentos, ver, ver.getTag()).commit();
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new dashboardFragment(), "Inicio");
        adapter.addFragment(new cursoFragment(), "Cursos");
        adapter.addFragment(new instructorFragment(), "Instructores");
        adapter.addFragment(new  verEspecialidadTodoFragment(), "Especialidades");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
            switch(position){
                case 0:
                    return "Inicio";
                case 1:
                    return "Curso";
                case 2:
                    return "Instructor";
                case 3:
                    return "Especialidad";

            }
            return null;
        }
    }
}