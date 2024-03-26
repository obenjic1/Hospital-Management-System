   
   		// Confirm delete role js function
function confirm_decision(name) {
    if (confirm("Are you sure you want to delete '" + name + "'?")) {
        var urlAction = "delete-role/" + encodeURIComponent(name);
        window.location.href = urlAction;
    } else {
        return false;
    }
    return true;
};