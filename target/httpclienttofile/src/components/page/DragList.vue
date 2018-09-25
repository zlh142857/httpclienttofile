<template>
    <div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="120px" el-input-width="200px">
                    <table style="width:900px;" id="table-form">
                        <tr>
                            <th colspan="2" ><h3>发文审批信息</h3></th>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="标题：">
                                    <el-input ></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item label="文种：">
                                    <el-input></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                 <el-form-item label="主送：">
                                    <el-input></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                 <el-form-item label="抄送：">
                                    <el-input></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="密级：" >
                                    <el-select v-model="form.region" placeholder="请选择">
                                        <el-option key="nei" label="内部" value="nei"></el-option>
                                        <el-option key="wai" label="外部" value="wai"></el-option>
                                        <el-option key="wu" label="无秘" value="wu"></el-option>
                                    </el-select>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item label="加急：">
                                    <el-select v-model="form.regions"  placeholder="请选择">
                                        <el-option key="jiaji" label="加急" value="jiaji"></el-option>
                                    </el-select>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="加急原因：">
                                    <el-input ></el-input>
                                </el-form-item>
                            </td>
                            <td> 
                                  <el-form-item label="发文部门：">
                                    <el-input ></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="拟稿人：">
                                    <el-input ></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                 <el-form-item label="拟稿人电话：">
                                    <el-input></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                 <el-form-item label="拟稿日期：">
                                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                                </el-form-item>
                            </td>
                            <td>
                                 <el-form-item label="反馈日期：">
                                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" style="width: 100%;"></el-date-picker>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="关键词(录入)：" >
                                    <el-input></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item label="关键词(识别)：" >
                                    <el-input ></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr class="sign">
                            <td>
                                <el-form-item label="部门领导签发：">
                                    <el-input type="textarea" disabled="disabled" rows="5"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item label="部门领导审批：">
                                    <el-input type="textarea" disabled="disabled" rows="5" ></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr id="boxFooter">
                            <th>
                                <el-form-item>
                                    <el-button type="primary" @click="onSubmit()" id="renderPdf">生成PDF格式</el-button>
                                </el-form-item>
                            </th>
                        </tr>
                    </table>
                    <!-- <el-form-item label="城市级联">
                        <el-cascader :options="options" v-model="form.options"></el-cascader>
                    </el-form-item>
                    <el-form-item label="多选框">
                        <el-checkbox-group v-model="form.type">
                            <el-checkbox label="步步高" name="type"></el-checkbox>
                            <el-checkbox label="小天才" name="type"></el-checkbox>
                            <el-checkbox label="imoo" name="type"></el-checkbox>
                        </el-checkbox-group>
                    </el-form-item> -->
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
import {htmlcanvas} from '../../../static/js/html2canvas.js'
import {pdfjs} from '../../../static/js/pdfjs.js'
export default {
    name: "baseform",
    data: function() {
        return {
            form: {
                name: "",
                region: "",
                regions: "",
                date1: "",
                date2: "",
                delivery: true,
                type: ["步步高"],
                resource: "小天才",
                desc: "",
                options: []
            }
        };
    },
    methods: {
        onSubmit() {
            this.$message.success("生成PDF文件成功！");
        }
    }
};
</script>

<style scope>
    table tr th h3{
        padding: 30px;
    }
    table td {
    border: 1px solid #ccc;
    height: 40px;
    line-height: 50px;
    }
    .el-form-item{
        margin-top: 5px;
        margin-bottom: 5px !important;
    }
    table th {
    text-align: center;
    }
    .el-input {
    width: 200px !important;
    }
    .el-form {
    margin-left: 50%;
    }
    .container-fiexd{
        z-index: 999;
        position:fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        width: 100%;
        height: 100%;
        border: none;
    }
    .container-fiexd table{
        margin-left: 50%;
        border: none;

    }
    #boxFooter .el-button{
        margin-left: 85%;
    }
    .sign textarea{
        resize:none;
        background: #fff !important;
    }
</style>
