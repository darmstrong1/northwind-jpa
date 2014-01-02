var category = namespace('co.da.nw.category');

category.urls = {
        'listUrl' : '/home/category/list',
        'createUrl' : '/home/category/create',
        'updateUrl' : '/home/category/update',
        'deleteUrl' : '/home/category/delete'
};

// Define resizeTab function.
category.resizeTab = function() {
    $("#categoryGrid").jqGrid('setGridWidth', $("#categoryJqgrid").width()-5, true);
}

// Call resizeTab when the window resizes.
$(window).resize(function() {
    category.resizeTab();
}).trigger('resize');

category.load = function() {
    $("#categoryGrid").jqGrid({
        url: category.urls.listUrl,
        datatype: 'json',
        mtype: 'GET',
        colNames:['Id', 'Name', 'Description'],
        colModel:[
            {name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
            //{name:'categoryId',index:'categoryId', width:55, editable:false, editoptions:{readonly:true, size:10}},
            {name:'name',index:'name', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
            {name:'description',index:'description', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}}
        ],
        postData: {},
        rowNum:10,
        rowList:[10,20,40,60],
        height: '100%',
        autowidth: true,
        rownumbers: true,
        pager: '#categoryPager',
        sortname: 'id',
        viewrecords: true,
        sortorder: "asc",
        caption:"Categories",
        emptyrecords: "Empty records",
        loadonce: false,
        loadComplete: function() {},
        jsonReader : {
            root: "rows",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            cell: "cell",
            id: "id"
        }
    });

    $("#categoryGrid").jqGrid('navGrid','#categoryPager',
            {edit:false, add:false, del:false, search:true},
            {}, {}, {}, 
            {   // search
                sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
                closeOnEscape: true, 
                multipleSearch: true, 
                closeAfterSearch: true
            }
    );
    
    $("#categoryGrid").navButtonAdd('#categoryPager',
            {   caption:"Add", 
                buttonicon:"ui-icon-plus", 
                onClickButton: category.addRow,
                position: "last", 
                title:"", 
                cursor: "pointer"
            } 
    );
    
    $("#categoryGrid").navButtonAdd('#categoryPager',
            {   caption:"Edit", 
                buttonicon:"ui-icon-pencil", 
                onClickButton: category.editRow,
                position: "last", 
                title:"", 
                cursor: "pointer"
            } 
    );
    
    $("#categoryGrid").navButtonAdd('#categoryPager',
        {   caption:"Delete", 
            buttonicon:"ui-icon-trash", 
            onClickButton: category.deleteRow,
            position: "last", 
            title:"", 
            cursor: "pointer"
        } 
    );

    // Toolbar Search
    $("#categoryGrid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
    
    //$("#categoryGrid").jqGrid('setGridHeight', $("#categoryJqgrid").height() – ($("#gbox_grid").height() – $('#gbox_grid .ui-jqgrid-bdiv').height()));
}

category.displayTab = function() {
    category.resizeTab();
}

// This is the first tab, so load it at startup.
$(document).ready(function() {
    category.load();
});

category.addRow = function() {
    $("#categoryGrid").jqGrid('setColProp', 'name', {editoptions:{readonly:false, size:10}});
    $("#categoryGrid").jqGrid('setColProp', 'description', {editoptions:{readonly:false, size:10}});
    
    // Get the currently selected row
    $('#categoryGrid').jqGrid('editGridRow','new',
            {
                url: category.urls.createUrl,
                datatype: 'json',
                editData: {},
                ajaxEditOptions: {
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    headers: { 
                        accept: 'application/json',
                        contentType: 'application/json' 
                    }
                },
                serializeEditData: function(data){
                    // Set the id to 0 since this is a new row. The id will get assigned when the object is persisted.
                    data.id = 0;
                    return util.convertToJSON(data);
                },
                recreateForm: true,
                beforeShowForm: function(form) {
                    $('#pData').hide();  
                    $('#nData').hide();
                },
                beforeInitData: function(form) {},
                closeAfterAdd: true,
                reloadAfterSubmit:true,
                afterSubmit : function(response, postdata) 
                { 
                    var result = eval('(' + response.responseText + ')');
                    var errors = "";
                    
                    if (result.success == false) {
                        for (var i = 0; i < result.messages.length; i++) {
                            errors +=  result.messages[i] + "<br/>";
                        }
                    }  else {
                        $('#categoryMsgbox').text('Entry has been added successfully');
                        $('#categoryMsgbox').dialog( 
                                {   title: 'Success',
                                    modal: true,
                                    buttons: {"Ok": function()  {
                                        $(this).dialog("close");} 
                                    }
                                });
                    }
                    // only used for adding new records
                    var newId = null;
                    
                    return [result.success, errors, newId];
                }
            });
} // end of category.addRow


category.editRow = function() {
    $("#categoryGrid").jqGrid('setColProp', 'name', {editoptions:{readonly:false, size:10}});
    $("#categoryGrid").jqGrid('setColProp', 'description', {editoptions:{readonly:false, size:10}});
    
    // Get the currently selected row
    var row = $('#categoryGrid').jqGrid('getGridParam','selrow');
    
    if( row != null ) {
    
        $('#categoryGrid').jqGrid('editGridRow', row,
            {   url: category.urls.updateUrl, 
                editData: {},
                ajaxEditOptions: {
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    headers: { 
                        accept: 'application/json',
                        contentType: 'application/json' 
                    }
                },
                serializeEditData: function(data){
                    // Send it as JSON.
                    return util.convertToJSON(data);
                },
                recreateForm: true,
                beforeShowForm: function(form) {
                    $('#pData').hide();  
                    $('#nData').hide();
                },
                beforeInitData: function(form) {},
                closeAfterEdit: true,
                reloadAfterSubmit:true,
                afterSubmit : function(response, postdata) 
                { 
                    var result = eval('(' + response.responseText + ')');
                    var errors = "";
                    
                    if (result.success == false) {
                        for (var i = 0; i < result.messages.length; i++) {
                            errors +=  result.messages[i] + "<br/>";
                        }
                    }  else {
                        $('#categoryMsgbox').text('Entry has been edited successfully');
                        $('#categoryMsgbox').dialog( 
                                {   title: 'Success',
                                    modal: true,
                                    buttons: {"Ok": function()  {
                                        $(this).dialog("close");} 
                                    }
                                });
                    }
                    // only used for adding new records
                    var newId = null;
                    
                    return [result.success, errors, newId];
                }
            });
    } else {
        $('#categoryMsgbox').text('You must select a record first!');
        $('#categoryMsgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}

category.deleteRow = function() {
    // Get the currently selected row
    var row = $('#categoryGrid').jqGrid('getGridParam','selrow');

    // A pop-up dialog will appear to confirm the selected action
    if( row != null ) 
        $('#categoryGrid').jqGrid( 'delGridRow', row,
            {   url: category.urls.deleteUrl, 
                recreateForm: true,
                beforeShowForm: function(form) {
                    //Change title
                    $(".delmsg").replaceWith('<span style="white-space: pre;">' +
                            'Delete selected record?' + '</span>');
                    //hide arrows
                    $('#pData').hide();  
                    $('#nData').hide();
                },
                reloadAfterSubmit:true,
                closeAfterDelete: true,
                serializeDelData: function (postdata) {
                      var rowdata = $('#categoryGrid').getRowData(postdata.id);
                      // append postdata with any information 
                      return {id: rowdata.id, oper: postdata.oper, username: rowdata.username};
                },
                afterSubmit : function(response, postdata) 
                { 
                    var result = eval('(' + response.responseText + ')');
                    var errors = "";
                    
                    if (result.success == false) {
                        for (var i = 0; i < result.messages.length; i++) {
                            errors +=  result.messages[i] + "<br/>";
                        }
                    }  else {
                        $('#categoryMsgbox').text('Entry has been deleted successfully');
                        $('#categoryMsgbox').dialog( 
                                {   title: 'Success',
                                    modal: true,
                                    buttons: {"Ok": function()  {
                                        $(this).dialog("close");} 
                                    }
                                });
                    }
                    // only used for adding new records
                    var newId = null;
                    
                    return [result.success, errors, newId];
                }
            });
    else {
        $('#categoryMsgbox').text('You must select a record first!');
        $('#categoryMsgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}
