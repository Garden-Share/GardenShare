
function searchListings() {

  print("search listing function called");
  // Declare variables
  var input, filter, table, tr, td, i;
  input = document.getElementById("listingSearch");
  filter = input.value.toUpperCase();
  table = document.getElementById("listingTable");
  tbody = document.getElementById("listingTableBody");
  tr = tbody.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
