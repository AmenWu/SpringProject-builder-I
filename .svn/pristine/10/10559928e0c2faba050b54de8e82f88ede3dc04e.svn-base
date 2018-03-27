<%
	String basePathTemp = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%> 

<link rel="stylesheet" href="<%=basePathTemp %>css/plugins/jqPaginator-1.2.0/jqPaginator.css" type="text/css" />
<div class="pagination-wrap">
<ul class="pagination" id="pagination1"></ul>
</div>

<script type="text/javascript">
	$("#search_data").click(function(){
		 $("input[name='pageCurrent']").val(1);
		 $("#pageForm").submit();
	})
	$("#search_clear").click(function (){
		 var objId = $("#pageForm");
		 $(objId).children().each(function(){
			var tagNames= $(this).prop('tagName');
			if(tagNames =='INPUT' || tagNames == 'input'){
				$(this).val("");
			}else if(tagNames == 'SELECT' || tagNames == 'select'){
				$(this).val("");
			}
		 })
		 $("#pageForm").submit();
	})
	var totalPages = '${page.totalPage}';
	var currentPage = '${page.pageNo}';
	if(parseInt(totalPages) > 0){
		 $.jqPaginator('#pagination1', {
		        totalPages: parseInt(totalPages),
		        visiblePages: 10,
		        currentPage: parseInt(currentPage),
		        onPageChange: function (num, type) {
		        }
		   });
	}
</script>

<style>
	.ibox-content{ position:relative; }
	.pagination-wrap{ float:right; height:46px; }
	.pagination-wrap ul{ margin:0; position:absolute; right:20px; bottom:20px; }
</style>
