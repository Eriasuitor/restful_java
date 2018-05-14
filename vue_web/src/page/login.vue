<template>
<div class="ui two column very relaxed grid bki myCol">
  <div class="column jira"><span>JIRA</span><br>项目开发管理系统</div>
  <div class="column loginCol">
    <div class="ui card myCard right floated">
    <form class="ui form loginPad">
  <div class="field">
    <label>员工名</label>
    <input type="text" placeholder="员工名">
  </div>
  <div class="field">
    <label>密码</label>
    <input type="password" placeholder="密码">
  </div>
  <div class="field">
    <a>
      <!-- <input type="checkbox" tabindex="0" class="hidden"> -->
      公司保密协议
    </a>
  </div>
  <button class="ui button float right floated" type="submit">登录</button>
</form>
</div>
  </div>
</div>
</template>
<script>
// import NewPorjectModal from '../components/newPorjectModal'
export default {
//   components: { NewPorjectModal },
  data () {
    return {
      projects: [],
      userId: 2,
      project: {},
      isSaving: false,
      log: {}
    }
  },
  created () {
  },
  methods: {
    logEvent: function () {
      $('.newLog')
        .modal({
          centered: true,
          blurring: false,
          inverted: false,
          closable: false,
          context: 'div2'
        })
        .modal('show')
    },
    newProject: function () {
      //   this.isSaving = true
      this.project.managerID = $('.dropdown.manager').dropdown('get value')
      this.project.participantsList = []
      $('.dropdown.participants')
        .dropdown('get value')
        .split(',')
        .forEach(sId => {
          this.project.participantsList.push({ staffId: sId })
        })
      this.$api.post(
        this.$apiUrl + '/projects?staffid=' + this.userId,
        this.project,
        data => {
          window.alert(data.information)
          this.refreshProjects()
        }
      )
    },
    refreshProjects: function () {
      this.$api.get(
        this.$apiUrl + '/projects?staffid=' + this.userId,
        null,
        data => {
          this.projects = data.projectNames
        }
      )
    },
    newProjectEvent: function () {
      $('.newProject')
        .modal({
          centered: true,
          inverted: false,
          blurring: false,
          closable: false,
          context: 'div2'
        })
        .modal('show')
    },
    empty: function () {
      $('.dropdown.participants').dropdown('clear')
    },
    search: function () {
      $('.ui.search').search({
        apiSettings: {
          onResponse: resp => {
            $.each(resp.projectNames, function (index, item) {
              item.url = '/projects/' + item.id
            })
            return resp
          },
          url: this.$apiUrl + '/projects?q={query}'
        },
        fields: {
          results: 'projectNames',
          title: 'name',
          url: 'url'
        },
        minCharacters: 1
      })
    },
    detail: function (pId) {
      this.$router.push({ path: '/projects/' + pId, replace: true })
    },
    
  },
  mounted () {
    $('.staffDropdown').dropdown({
      apiSettings: {
        url: this.$apiUrl + '/staff?name={query}'
      },
      maxSelections: 1
    })
    $('.staffDropdownComplex').dropdown({
      apiSettings: {
        url: this.$apiUrl + '/staff?name={query}'
      }
    })
  }
}
</script>
<style scoped>
.breadcrumb {
  margin-bottom: 15px;
}
.ellipsis {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
.clickable:hover{
  cursor:pointer;
}
.note{
  font-size: 0.5em;
}
.loginPad{
  padding: 1em;
}
.bki{
  background-image: url('https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4060097258,647715395&fm=27&gp=0.jpg');
  background-repeat: repeat;
}
.myCard{
  margin: auto;
}
.jira{
  position: absolute;
        top: 50%;
        transform: translateY(40%);
  text-align: right;
}
.jira span{
  font-size: 9em;
}
.myCol{
     position: absolute;
        top: 50%;
        width: 100%;
        transform: translateY(-50%);
}
</style>
