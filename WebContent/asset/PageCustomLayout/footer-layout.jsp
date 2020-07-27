		</div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy;2020 - University of Information Technology</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
  <!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
        <a class="btn btn-primary" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
      </div>
    </div>
  </div>
</div>
 
<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/asset/vendor/jquery/jquery.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/asset/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script> 

<!-- Core plug-in JavaScript-->
<script src="${pageContext.request.contextPath}/asset/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script> 

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/asset/js/sb-admin-2.min.js" type="text/javascript"></script> 

<!-- Page level plug-ins -->
<script src="${pageContext.request.contextPath}/asset/vendor/chart.js/Chart.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/asset/vendor/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/asset/vendor/datatables/dataTables.bootstrap4.min.js" type="text/javascript"></script>

<!-- Page level custom scripts -->
<script src="${pageContext.request.contextPath}/asset/js/demo/chart-area-demo.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/asset/js/demo/chart-pie-demo.js" type="text/javascript"></script> 

<!-- Custom JavaScript-->
<script src="${pageContext.request.contextPath}/asset/js/my-script.js" type="text/javascript"></script> 
</body>
</html>