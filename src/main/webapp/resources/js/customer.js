var customer = namespace('northwind.customer');

customer.urls = {
        'listUrl' : '/home/customer/list',
        'createUrl' : '/home/customer/create',
        'updateUrl' : '/home/customer/update',
        'deleteUrl' : '/home/customer/delete'
};

$(window).resize(function() {
    $("#customerGrid").jqGrid('setGridWidth', $("#customerJqgrid").width()-5, true);
}).trigger('resize');

$(document).ready(function() {
    $(function() {
        $("#customerGrid").jqGrid({
            url: customer.urls.listUrl,
            datatype: 'json',
            mtype: 'GET',
            colNames:['Id', 'Company Name', 'Contact Name', 'Contact Title', 'Address', 'City', 'Region',
                      'Postal Code', 'Country', 'Phone', 'Fax'],
            colModel:[
                {name:'customerId',index:'customerId', width:55, editable:true, editrules:{required:true},editoptions:{size:10}},
                {name:'companyNm',index:'companyNm', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
                {name:'contactNm',index:'contactNm', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'contactTitle',index:'contactTitle', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'address',index:'address', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'city',index:'city', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'region',index:'region', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'postalCode',index:'postalCode', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'country',index:'country', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'phone',index:'phone', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}},
                {name:'fax',index:'fax', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}}
            ],
            postData: {},
            rowNum:10,
            rowList:[10,20,40,60],
            height: '100%',
            autowidth: true,
            rownumbers: true,
            pager: '#customerPager',
            sortname: 'customerId',
            viewrecords: true,
            sortorder: "asc",
            caption:"Customers",
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
                id: "customerId"
            }
        });
    
        $("#customerGrid").jqGrid('navGrid','#customerPager',
                {edit:false, add:false, del:false, search:true},
                {}, {}, {}, 
                {   // search
                    sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
                    closeOnEscape: true, 
                    multipleSearch: true, 
                    closeAfterSearch: true
                }
        );
        
        $("#customerGrid").navButtonAdd('#customerPager',
                {   caption:"Add", 
                    buttonicon:"ui-icon-plus", 
                    onClickButton: customer.addRow,
                    position: "last", 
                    title:"", 
                    cursor: "pointer"
                } 
        );
        
        $("#customerGrid").navButtonAdd('#customerPager',
                {   caption:"Edit", 
                    buttonicon:"ui-icon-pencil", 
                    onClickButton: customer.editRow,
                    position: "last", 
                    title:"", 
                    cursor: "pointer"
                } 
        );
        
        $("#customerGrid").navButtonAdd('#customerPager',
            {   caption:"Delete", 
                buttonicon:"ui-icon-trash", 
                onClickButton: customer.deleteRow,
                position: "last", 
                title:"", 
                cursor: "pointer"
            } 
        );
    
        // Toolbar Search
        $("#customerGrid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
        
        //$("#customerGrid").jqGrid('setGridHeight', $("#customerJqgrid").height() – ($("#gbox_grid").height() – $('#gbox_grid .ui-jqgrid-bdiv').height()));
    });
});

customer.addRow = function() {
    $("#customerGrid").jqGrid('setColProp', 'customerId', {editoptions:{readonly:false, size:10}});
    $("#customerGrid").jqGrid('setColProp', 'companyNm', {editoptions:{readonly:false, size:10}});
    $("#customerGrid").jqGrid('setColProp', 'contactNm', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'contactTitle', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'address', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'city', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'region', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'postalCode', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'country', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'phone', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'fax', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    
    // Get the currently selected row
    $('#customerGrid').jqGrid('editGridRow','new',
            {
                url: customer.urls.createUrl,
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
                    return convertToJSON(data);
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
                        $('#customerMsgbox').text('Entry has been added successfully');
                        $('#customerMsgbox').dialog( 
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
} // end of customer.addRow


customer.editRow = function() {
    $("#customerGrid").jqGrid('setColProp', 'customerId', {editoptions:{readonly:false, size:10}});
    $("#customerGrid").jqGrid('setColProp', 'companyNm', {editoptions:{readonly:false, size:10}});
    $("#customerGrid").jqGrid('setColProp', 'contactNm', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'contactTitle', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'address', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'city', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'region', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'postalCode', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'country', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'phone', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    $("#customerGrid").jqGrid('setColProp', 'fax', {editoptions:{readonly:false, size:10}}, {editrules:{required:false}});
    
    // Get the currently selected row
    var row = $('#customerGrid').jqGrid('getGridParam','selrow');
    
    if( row != null ) {
    
        $('#customerGrid').jqGrid('editGridRow', row,
            {   url: customer.urls.updateUrl, 
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
                    return convertToJSON(data);
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
                        $('#customerMsgbox').text('Entry has been edited successfully');
                        $('#customerMsgbox').dialog( 
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
        $('#customerMsgbox').text('You must select a record first!');
        $('#customerMsgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}

customer.deleteRow = function() {
    // Get the currently selected row
    var row = $('#customerGrid').jqGrid('getGridParam','selrow');

    // A pop-up dialog will appear to confirm the selected action
    if( row != null ) 
        $('#customerGrid').jqGrid( 'delGridRow', row,
            {   url: customer.urls.deleteUrl, 
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
                      var rowdata = $('#customerGrid').getRowData(postdata.id);
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
                        $('#customerMsgbox').text('Entry has been deleted successfully');
                        $('#customerMsgbox').dialog( 
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
        $('#customerMsgbox').text('You must select a record first!');
        $('#customerMsgbox').dialog( 
                {   title: 'Error',
                    modal: true,
                    buttons: {"Ok": function()  {
                        $(this).dialog("close");} 
                    }
                });
    }
}
