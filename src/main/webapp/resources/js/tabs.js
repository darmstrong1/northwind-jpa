// This file contains the logic to display the tabs. For all but the category tab, the tab will not load the data from
// the DB table until the tab is first activated. All displayTab functions will also resize the tab to ensure it
// displays correctly.
var tabs = namespace('co.da.nw.tabs');

tabs.displayTab = function(tabName) {
    switch(tabName) {
    case "#customer":
        customer.displayTab();
        break;
        
    case "#category":
        category.displayTab();
    default:
        break;
    }
}

$(document).ready(function() {
    $("#tabs").tabs({
        activate:function(e,ui){
            var active = $('#tabs').tabs('option', 'active');
            var tabName = $("#tabs ul>li a").eq(active).attr("href")
            tabs.displayTab(tabName);
        }//,
    
        //beforeActivate: function (e,ui) {
            //window.location.hash = ui.newPanel.selector;
        //}
    });
    
//    $(window).on('hashchange',function(){
  //      tabs.displayTab(location.hash);
        //$('h1').text(location.hash.slice(1));
    //});
    
    // Append '#' to the window location so "Back" returns to the selected tab
    // after a redirect or a full page refresh (e.g. Views tab).

    // However, note this general disclaimer about going back to previous tabs: 
    // http://docs.jquery.com/UI/API/1.8/Tabs#Back_button_and_bookmarking

    $("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });
    
    
});
