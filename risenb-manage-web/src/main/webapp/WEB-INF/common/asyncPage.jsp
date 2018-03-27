<%
	String basePathTemp = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%> 

<link rel="stylesheet" href="<%=basePathTemp %>css/plugins/jqPaginator-1.2.0/jqPaginator.css" type="text/css" />
<script src="<%=basePathTemp %>js/asyncPage.js" type="text/javascript"></script>
<div class="pagination-wrap">
<ul class="pagination" id="pagination1"></ul>
</div>

<script type="text/javascript">
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
	.pagination-wrap ul{ margin-top:15px; position:relative; right:1px; bottom:10px; }
</style>
