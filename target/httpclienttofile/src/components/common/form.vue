<template>

     <div id="sample">
     	  <!--accept定义接收的文件类型,这里只接受图片-->
     	  <input id="fileinput" @change="uploading($event)" type="file" accept="image/*">
           <button  @click="submit($event)">123</button>
           <!--图片展示-->
           <img :src="src"/>
     </div>
</template>

<script>
export default {
  name: 'sample',

  data () {

    return {

        file:'',

        src:''

    };

  },

  methods:{
  	uploading(event){
  	this.file = event.target.files[0];//获取文件
  	    var windowURL = window.URL || window.webkitURL;
        this.file = event.target.files[0];
        //创建图片文件的url
        this.src = windowURL.createObjectURL(event.target.files[0]);
  	},
  	submit(){
  	event.preventDefault();//取消默认行为
  	let formdata = new FormData();
  	formdata.append('file',this.file);

  	let config = {
            headers: {
                'Content-Type': 'multipart/form-data'  //之前说的以表单传数据的格式来传递fromdata
            }
        };
        this.$http.post('/upload', formData, config).then( (res) => {
       //做处理
    }).catch((error) =>{

    });
  	}
  }
};

// 作者：johe_jianshu
// 链接：https://www.jianshu.com/p/84e94cc446c0
// 來源：简书
// 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
</script>

<style lang="css" scoped>

</style>
