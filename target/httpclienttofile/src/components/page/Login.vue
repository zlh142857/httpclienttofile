<template>
    <div class="login-wrap">
        <div class="ms-title">后台管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="name">
                    <el-input v-model="ruleForm.name" placeholder="输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="输入密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    import Qs from 'qs'
    export default {
        data: function(){
            return {
                ruleForm: {
                    name: 'admin',
                    password: '123'
                },
                rules: {
                    name: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm() {
                let data = {
                    name:this.ruleForm.name,
                    password:this.ruleForm.password,
                }
                this.$axios({
                    headers: {
                        'deviceCode': 'A95ZEF1-47B5-AC90BF3'
                    },
                    method: 'post',
                    url: '/api/user/login.do',
                    data: Qs.stringify(data),
                }) .then((res) => {
                    console.log(data)
                    console.log(res.data);
                    console.log(res.status)
                    //console.log(JSON.parse(res.data))
                    this.$router.push({path:"/Dashboard"})
                    
                }) .catch((error)=> {
                    console.log(error);
                    alert("输入账号密码错误")
                });
                
            }
        }
    }
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
}
.ms-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  color: #fff;
}
.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  height: 160px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}
.ms-login .demo-ruleForm{
    position: absolute;
    top: 25%;
    left: 25%;
    margin: 0;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
}
</style>