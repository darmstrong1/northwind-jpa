var urls = {
        'listUrl' : '/home/category/list',
        'createUrl' : '/home/category/create',
        'updateUrl' : '/home/category/update',
        'deleteUrl' : '/home/category/delete'
};


$(document).ready(function() {
    $(function() {
        $("#grid").jqGrid({
            url: urls.listUrl,
            datatype: 'json',
            mtype: 'GET',
            colNames:['Id', 'Name', 'Description'],
            colModel:[
                {name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
                //{name:'categoryId',index:'categoryId', width:55, editable:false, editoptions:{readonly:true, size:10}},
                {name:'name',index:'name', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
                {name:'description',index:'description', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}}
            ],
            postData: {},
            rowNum:10,
            rowList:[10,20,40,60],
            height: 240,
            autowidth: true,
            rownumbers: true,
            pager: '#pager',
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
    
        $("#grid").jqGrid('navGrid','#pager',
                {edit:false, add:false, del:false, search:true},
                {}, {}, {}, 
                {   // search
                    sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
                    closeOnEscape: true, 
                    multipleSearch: true, 
                    closeAfterSearch: true
                }
        );
        
        $("#grid").navButtonAdd('#pager',
                {   caption:"Add", 
                    buttonicon:"ui-icon-plus", 
                    onClickButton: addRow,
                    position: "last", 
                    title:"", 
                    cursor: "pointer"
                } 
        );
        
        $("#grid").navButtonAdd('#pager',
                {   caption:"Edit", 
                    buttonicon:"ui-icon-pencil", 
                    onClickButton: editRow,
                    position: "last", 
                    title:"", 
                    cursor: "pointer"
                } 
        );
        
        $("#grid").navButtonAdd('#pager',
            {   caption:"Delete", 
                buttonicon:"ui-icon-trash", 
                onClickButton: deleteRow,
                position: "last", 
                title:"", 
                cursor: "pointer"
            } 
        );
    
        // Toolbar Search
        $("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
        
        //$("#grid").jqGrid('setGridHeight', $("#jqgrid").height() – ($("#gbox_grid").height() – $('#gbox_grid .ui-jqgrid-bdiv').height()));
    });
});

$.postJSON = function(url, data, callback) {
    return jQuery.ajax({
        /*headers: { 
            Accept: 'application/json',
            Content-Type: 'application/json' 
        },*/        
        type: 'POST',
        url: url,
        data: JSON.stringify(data),
        contentType: 'application/json',
        mimeType: 'application/json',
        dataType: 'json',
        success: callback,
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }        
    });
};

function newCategory(data) {
  $.postJSON(urls.createUrl, data, function(data) {
    console.debug("Inserted: " + data);
  });
}

function addRow() {
    $("#grid").jqGrid('setColProp', 'name', {editoptions:{readonly:false, size:10}});
    $("#grid").jqGrid('setColProp', 'description', {editoptions:{readonly:false, size:10}});
    
    // Get the currently selected row
    $('#grid').jqGrid('editGridRow','new',
            {
                url: urls.createUrl,
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
                    // First, save the oper value and then delete it.
                    // We don't need it in the json value that goes to the server.
                    var operVal = data.oper;
                    delete data['oper'];
                    data.id = 0;
                    var jdata = JSON.stringify(data);
                    // Now, add oper back.
                    data.oper = operVal;
                    //return $.post(urls.createUrl, jdata, null, 'json');
                    return jdata;
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
                        $('#msgbox').text('Entry has been added successfully');
                        $('#msgbox').dialog( 
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
} // end of addRow


function editRow() {
    $("#grid").jqGrid('setColProp', 'name', {editoptions:{readonly:false, size:10}});
    $("#grid").jqGrid('setColProp', 'description', {editoptions:{readonly:false, size:10}});
    
    // Get the currently selected row
    var row = $('#grid').jqGrid('getGridParam','selrow');
    
    if( row != null ) {
    
        $('#grid').jqGrid('editGridRow', row,
            {   url: urls.updateUrl, 
                editData: {},
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
                        $('#msgbox').text('Entry has been edited successfully');
                        $('#msgbox').dialog( 
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
        $('#msgbox').text('You must select a record first!');
        $('#msgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}

function deleteRow() {
    // Get the currently selected row
    var row = $('#grid').jqGrid('getGridParam','selrow');

    // A pop-up dialog will appear to confirm the selected action
    if( row != null ) 
        $('#grid').jqGrid( 'delGridRow', row,
            {   url: urls.deleteUrl, 
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
                      var rowdata = $('#grid').getRowData(postdata.id);
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
                        $('#msgbox').text('Entry has been deleted successfully');
                        $('#msgbox').dialog( 
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
        $('#msgbox').text('You must select a record first!');
        $('#msgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}
