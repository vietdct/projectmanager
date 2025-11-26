
// $(document).ready(function() {
// 	$('.btn-delete').click(function() {
// 		var id = $(this).attr("data-user-id")
// 		This = $(this)

// 		$.ajax({
// 			method: "GET",
// 			url: "http://localhost:8080/myproject/api/user?id=" + id,

// 		})
// 			.done(function(result) {
// 				if (result.data) {
// 					This.closest('tr').remove()
// 				} else {
// 					alert(result.messge)
// 				}

// 			});
// 	})
// 	$('.btn-search').click(function() {
// 	    var name = $(this).attr("data-user-name");

// 	    $.ajax({
// 	      method: "GET",
// 	      url: "http://localhost:8080/myproject/api/user?name=" + encodeURIComponent(name),
// 	      dataType: "json"
// 	    })
// 	    .done(function(res) {
// 	      if (res && res.data) {
// 	        // res.data là List<User>
// 	        renderUsersToTable(res.data);
// 	      } else {
// 	        alert(res.message || 'Không tìm thấy dữ liệu');
// 	      }
// 	    })
// 	    .fail(function(xhr) {
// 	      alert("Lỗi gọi API: " + xhr.status);
// 	    });
// 	  });
// 	});
