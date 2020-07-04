function checkConfirmPassword(input) {
    if (input.value != document.getElementById('new_password').value) {
        input.setCustomValidity('Password Must be Matching.');
    } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
    }
}
$(document).ready(function() {
  $('#dataTable').DataTable();
});
$(document).ready(function() {
  $('#dataTable1').DataTable();
});
$(document).ready(function() {
  $('#dataTable2').DataTable();
});