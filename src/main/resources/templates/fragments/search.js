
function search() {
  var input, select, minEl, maxEl, min, max, filter, selectFilter, table, tbody, tr, td, i;
  input = document.getElementById("listingSearch");
  select = document.getElementById("weightSelect");
  minEl = document.getElementById("minWeight");
  maxEl = document.getElementById("maxWeight");

  min = minEl.value;
  max = maxEl.value;
  filter = input.value.toUpperCase();
  selectFilter = select.value.toUpperCase();
  table = document.getElementById("listingTable");
  tbody = document.getElementById("listingTableBody");
  tr = tbody.getElementsByTagName("tr");

  for (var i = 0; i < tr.length; i++) {

    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (selectFilter == "BOTH") {
        tr[i].style.display = "";
      } else {
        if (td.innerHTML.toUpperCase().indexOf(selectFilter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
          continue;
        }
      }
    }

    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      if (min >= max) {
        tr[i].style.display = "";
      } else {
        weight = parseInt(td.innerHTML);
        if (weight < min || weight > max) {
          tr[i].style.display = "none";
          continue;
        } else {
          tr[i].style.display = "";
        }
      }
    }

    if (filter.length > 0) {
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

}
