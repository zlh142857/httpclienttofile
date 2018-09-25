<template>
    <div class="">
        <div class="crumbs">
            <!-- <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-message"></i> 收文</el-breadcrumb-item>
            </el-breadcrumb> -->
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane :label="`发送文件`" name="first">
                  <div class="subForm">
                      <form id="uploadForm" target="nm_iframe" action="http://172.16.107.114:8080/sendFile.do" method="post" enctype="multipart/form-data">
                        <div class="text item">
                        <label for="">收件人：</label>
                        <select v-model="formAll.sec" multiple="multiple" size="4" placeholder="请选择"  name="userids" class="kongs">
                            <option
                              v-for="item in options"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value"
                            >
                              <input type="hidden" >
                              {{item.label}}
                            </option>
                          </select>
                        </div>
                        <div class="text item kongs">
                          <label for="">上传文件：</label>
                          <input type="file"  name="file"/>
                        </div>
                        <div class="text item">
                          <input id="upload" type="submit" @click="submit()" class="sub-btn" value="发送" />
                        </div>
                      </form>
                      <iframe id="id_iframe" name="nm_iframe" width="300px" height="100px" style="display:none;"></iframe>
                    </div>
                </el-tab-pane>
                
            </el-tabs>
        </div>
    </div>
</template>

<script>
export default {
  name: "tabs",
  data() {
    return {
      message: "first",
      formAll: {
        sec: "",
        file: ""
      },
      options: [
        {
          value: "1",
          label: "苏先生",
          userids: "userids"
        },
        {
          value: "2",
          label: "李先生",
          userids: "userids"
        },
        {
          value: "3",
          label: "马先生",
          userids: "userids"
        },
        {
          value: "4",
          label: "刘女士",
          userids: "userids"
        }
      ]
    };
  },
  methods: {
    submit(){
      $(".kongs input").val("")
      $("select").val("")
      this.$message.success(`添加成功`);
    }
  }
};
</script>

<style>
.message-title {
  cursor: pointer;
}
.handle-row {
  margin-top: 30px;
}
.subForm {
  position: relative;
  left: 0px;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.sub-btn {
  width: 80px;
  height: 30px;
  line-height: 30px;
  background: #1b92ee;
  color: #fff;
  border: 0;
  border-radius: 5px;
}
select {
  width: 150px;
  height: 50px;
  line-height: 50px;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  text-align: center;
  border-radius: 5px;
}
select option{
  background: #FDDD9B;
}
</style>

