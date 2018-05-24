<template>
  <div class="ui two column very relaxed grid bki myCol">
    <div class="column jira"><span>JIRA</span><br>项目开发管理系统</div>
    <div class="column">
      <div class="ui card myCard">
        <form class="ui form loginPad">
          <div class="field">
            <label>员工名</label>
            <input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="员工名" v-model="loginInfo.id">
          </div>
          <div class="field">
            <label>密码</label>
            <input type="password" placeholder="密码" v-model="loginInfo.password">
          </div>
          <div class="field">
            <a>
              公司保密协议
            </a>
          </div>
          <div class="ui button float right floated" @click="login()">登录</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  // import NewPorjectModal from '../components/newPorjectModal'
  export default {
    //   components: { NewPorjectModal },
    data() {
      return {
        loginInfo: {}
      }
    },
    created() {},
    methods: {
      login: function() {
        this.$api.post(
          this.$apiUrl + '/login?',
          this.loginInfo,
          data => {
            console.log(data)
            if (data.responseCode === 401) {
              window.alert(data.information)
            } else {
              window.localStorage.setItem('token', data.information)
              window.localStorage.setItem('userId', parseInt(this.loginInfo.id))
              let toAddress = window.localStorage.getItem('toLogin') ?
                window.localStorage.getItem('toLogin') : '/projects'
                console.log(toAddress)
              this.$router.push({
                path: toAddress,
                replace: true
              })
            }
          }
        )
      }
    },
    mounted() {
  
    }
  }
</script>

<style scoped>
  .loginPad {
    padding: 1em;
  }
  
  .bki {
    background-image: url('https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4060097258,647715395&fm=27&gp=0.jpg');
    background-repeat: repeat;
  }
  
  .myCard {
    margin: auto;
  }
  
  .jira {
    position: absolute;
    top: 50%;
    transform: translateY(40%);
    text-align: right;
  }
  
  .jira span {
    font-size: 9em;
  }
  
  .myCol {
    position: absolute;
    top: 50%;
    width: 100%;
    transform: translateY(-50%);
  }
</style>
