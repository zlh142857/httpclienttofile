<template>
    <div class="">
        <div class="crumbs">
            <!-- <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-message"></i> 收文</el-breadcrumb-item>
            </el-breadcrumb> -->
        </div>
        <div class="container">
            <el-tabs v-model="message">
                
                <el-tab-pane :label="`群发文件`" >
                   <el-form ref="form" :model="form" label-width="300px">
                        <el-form-item label="收件人：" >
                            <el-select v-model="value5" collapse-tags multiple placeholder="请选择">
                                <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="文件：" >
                            <el-upload style="width:300px;" ></el-upload>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" >提交</el-button>
                        </el-form-item>
                    </el-form>
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
      form: {
        fAdderss: "",
        name: ""
      },
      options: [
        {
          value: "选项1",
          label: "苏先生"
        },
        {
          value: "选项2",
          label: "李先生"
        },
        {
          value: "选项3",
          label: "马先生"
        },
        {
          value: "选项4",
          label: "刘女士"
        },
        {
          value: "选项5",
          label: "王先生"
        }
      ],
      value5:[]
    };
  },
  methods: {
      
  },
  computed: {
    unreadNum() {
      return this.unread.length;
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
</style>

