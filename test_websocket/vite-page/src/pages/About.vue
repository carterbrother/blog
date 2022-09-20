<template>
  <h1>prod 环境测试</h1>
  <button @click="initLink">建立连接</button>

  <button @click="clickButton">发送消息到websocket</button>
  <button @click="closeButton">关闭消息到websocket</button>
</template>
<script>
import {reactive, toRefs} from 'vue'
import { ElMessage } from 'element-plus'

export default {
  setup(){

    const data = reactive({
      userId: '1111',
      socket : null
    });

    const initLink = (val) => {

      var socketUrl ="https://cube.zen-game.com/api/common//websocket/link/" + data.userId+"/carter";
      socketUrl = socketUrl.replace("https", "ws").replace("http", "ws");
      console.log(socketUrl);
      var socket = data.socket;
      if (socket != null) {
        socket.close();
        socket = null;
      }
      //打开事件
      socket = new WebSocket(socketUrl);
      //打开事件
      socket.onopen = function() {
        console.log("websocket已打开");
        //socket.send("这是来自客户端的消息" + location.href + new Date());
        data.socket = socket;
      };

      //关闭事件
      socket.onclose = function() {
        console.log("websocket已关闭");
      };
      //发生了错误事件
      socket.onerror = function() {
        console.log("websocket发生了错误");
      };

      //获得消息事件
      socket.onmessage = function(msg) {
        console.log( JSON.stringify(msg));
        //发现消息进入    开始处理前端触发逻辑
        ElMessage({
          showClose: true,
          message: JSON.stringify(msg.data),
          type: 'success',
        })


      }


    }

    const clickButton = (val) => {
      data.socket.send(JSON.stringify({'a':'aaaaaaaa'}));

    }


    const closeButton = (val) => {
      data.socket.close(3000,"test close");

    }

    return {
      ...toRefs(data),
      clickButton,
      closeButton,
      initLink

    }
  }

}
</script>