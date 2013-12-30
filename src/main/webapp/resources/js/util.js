// This function takes a data object and converts it to JSON. The data object should have
// an oper key/value. Remove that before converting to JSON and afterward, put it back.
// If oper is undefined, just convert to JSON. add is a boolean. If it is true, set the
// id to 0.
function convertToJSON(data, add) {
    if(add) {
        data.id = 0;
    }
    var operVal = data.oper;
    var jdata;
    if(operVal != undefined) {
        delete data['oper'];
        jdata = JSON.stringify(data);
        data.oper = operVal;
    } else {
        jdata = JSON.stringify(data);
    }
    
    return jdata;
}

// Function for declaring namespace variables
function namespace(namespaceString) {
    var parts = namespaceString.split('.'),
        parent = window,
        currentPart = '';    
        
    for(var i = 0, length = parts.length; i < length; i++) {
        currentPart = parts[i];
        parent[currentPart] = parent[currentPart] || {};
        parent = parent[currentPart];
    }
    
    return parent;
}
