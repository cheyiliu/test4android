1. see lib.metro.sample first.
2. used in xml
    <lib.metro.MyHorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <lib.metro.MetroView
            android:id="@+id/metroView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </lib.metro.MyHorizontalScrollView>
3. in java code,
    metroView = (MetroView) findViewById(R.id.metroView);
    metroAdapter = new MetroAdapter() {
    metroView.setAdapter(metroAdapter);
4. some notes
    use layout_margin*** to adjust the position
    as the 3 layer, so no animation support for the inner view
    