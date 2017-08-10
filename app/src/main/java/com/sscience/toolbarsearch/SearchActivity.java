package com.sscience.toolbarsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * @author SScience
 * @description
 * @email chentushen.science@gmail.com
 * @data 2017/8/3
 */

public class SearchActivity extends AppCompatActivity {

    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private SearchView mSearchView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("搜索");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Toolbar返回按钮的点击事件，当搜索框显示时，关闭搜索框，否则就关闭当前界面，配合mSearchView.onActionViewExpanded()
        // 而app:showAsAction="always|collapseActionView" 默认已实现
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) {
                    mSearchAutoComplete.setText("");
                    mSearchView.setIconified(true);
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu_search : app:showAsAction="always"
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) search.getActionView();
        mSearchView.setOnQueryTextListener(mQueryTextListener);
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text); // 搜索框的输入框

        // 搜索历史列表
        final String[] testHistory = {"java", "kotlin", "python", "go", "c"};
        mSearchAutoComplete.setThreshold(0); // 输入多少个字符后开始匹配
        mSearchAutoComplete.setAdapter(new ArrayAdapter<>(this, R.layout.search_item, R.id.tv_history, testHistory));
        mSearchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchView.setQuery(testHistory[position], true);
            }
        });

        ////  一，Toolbar主题：ThemeOverlay.AppCompat.Dark.ActionBar（搜索框内无下划线和放大镜图标）
        // 三种设置展开搜索框的方式
//        mSearchView.setIconified(false); // 1，右侧有叉叉，可以关闭搜索框
        mSearchView.onActionViewExpanded(); // 2，右侧无叉叉，有输入内容后有叉叉，不能关闭搜索框
//        mSearchView.setIconifiedByDefault(false); // 3，左侧有放大镜(在搜索框外)，软键盘不弹出，右侧无叉叉，有输入内容后有叉叉，不能关闭搜索框
        // 在使用 setIconifiedByDefault(false) 时，取消输入框外的搜索图标
//        ImageView searchViewIcon = mSearchView.findViewById(R.id.search_mag_icon);
//        ViewGroup linearLayoutSearchView = (ViewGroup) searchViewIcon.getParent();
//        linearLayoutSearchView.removeView(searchViewIcon);

        ////  二，Toolbar主题：ThemeOverlay.AppCompat.Dark（搜索框内有下划线和放大镜图标）
        // 三种展开搜索框的方式中，搜索框内有下划线和放大镜图标

        return super.onCreateOptionsMenu(menu);
    }

    SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(SearchActivity.this, "搜索关键词为：" + query, Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
