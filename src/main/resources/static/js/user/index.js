

function deleteField(event) {
    if(confirm("Delete user? This action cannot be undone!")) {
        alert("User sucessfully deleted")
    } else {
        alert("User deletion cancelled.")
        event.preventDefault();
    }
}

