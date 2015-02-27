               <div class="form-group">
                       <label class="col-lg-2 control-label">点击重启AP：</label>
                       <div class="col-lg-10 "><input type="button"  id="restartAp"   value="重启"/></div>
             </div>

               $('#restartAp').bind('click',function(){
                         var count = 3;
                 var countdown = setInterval(CountDown, 1000);
                 function CountDown() {
                     $("#restartAp").attr("disabled", true);
                     $("#restartAp").val("请等待" + count + " 秒!");
                     if (count == 0) {
                         $("#restartAp").val("重启").removeAttr("disabled");
                         clearInterval(countdown);
                         alert("重启失败");
                     }
                     count--;
                 }
               });

