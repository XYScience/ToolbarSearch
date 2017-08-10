# ToolbarSearch
A search widget on toolbar    

使用    
---------------    
**一，在当前页面搜索**    
在`menu`文件中设置属性`app:showAsAction="always|collapseActionView"`。    
效果：搜索框默认不显示，点击搜索图标后展开；搜索框外左侧有返回按钮，当搜索框显示时，点击返回按钮则关闭搜索框，否则就关闭当前界面；
右侧无叉叉图标，输入内容后才出现叉叉图标。     
     
**二，点击搜索图标后跳转新页面，并打开搜索框（默认打开搜索框有三种方法，并有两种效果）**            
1，搜索框内无下划线和放大镜图标（`Toolbar`主题：`ThemeOverlay.AppCompat.Dark.ActionBar`）    
* `mSearchView.setIconified(false);` // 1，右侧有叉叉，可以关闭搜索框。         
* `mSearchView.onActionViewExpanded()`; // 2，右侧无叉叉，有输入内容后有叉叉，不能关闭搜索框。         
* `mSearchView.setIconifiedByDefault(false)`; // 3，左侧有放大镜(在搜索框外)，软键盘不弹出，右侧无叉叉，有输入内容后有叉叉，不能关闭搜索框。    

使用`mSearchView.onActionViewExpanded();`时，实现‘一’中的效果：    
```
// Toolbar返回按钮的点击事件，当搜索框显示时，关闭搜索框，否则就关闭当前界面，配合mSearchView.onActionViewExpanded()
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
```
        
2，搜索框内有下划线和放大镜图标（`Toolbar`主题：`ThemeOverlay.AppCompat.Dark`）    
略
   
**三，自定义SearchView**    
1，自定义光标颜色    
```
<style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar">    
        <!--设置光标颜色方式1-->
        <item name="colorAccent">@android:color/white</item>      
        <!--设置光标颜色方式2-->
        <!--<item name="autoCompleteTextViewStyle">@style/CursorColor</item>--> 
</style> 

<!--光标颜色-->
<style name="CursorColor" parent="Widget.AppCompat.AutoCompleteTextView">
        <item name="android:textCursorDrawable">@drawable/cursor</item>
</style>
```     
2，自定义`SearchView` ，针对`ThemeOverlay.AppCompat.Dark`主题（搜索框内有下划线和放大镜图标）    
```
<style name="AppTheme.NoActionBar.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark">
        <item name="searchViewStyle">@style/CustomSearchView</item>
        <!--两种设置光标颜色方式-->
        <item name="colorAccent">@android:color/white</item>
        <!--<item name="autoCompleteTextViewStyle">@style/CursorColor</item>-->
</style>    

<style name="CustomSearchView" parent="Widget.AppCompat.SearchView">
        <!--修改搜索框提示文字-->
        <item name="defaultQueryHint">请输入关键字</item>
        <!--去除搜索框内左边的放大镜图标-->
        <item name="searchHintIcon">@null</item>
        <!--去除搜索框下划线-->
        <item name="queryBackground">@null</item>
</style>
```     
      
ScreenShot    
---------------    
* 在当前页面搜索    
![image](https://github.com/XYScience/ToolbarSearch/raw/master/screenshot/toolbarSearch1.gif)    
     
* 点击搜索图标后跳转新页面，并打开搜索框    
![image](https://github.com/XYScience/ToolbarSearch/raw/master/screenshot/toolbarSearch2.gif)    
