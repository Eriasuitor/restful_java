<template>
    <div2>

<div class="ui segment">
  <a class="ui blue ribbon label"> 尚未完成 </a>
  <h3 class="ui dividing header">年代建设
            <div class="sub header">没有描述</div>
        </h3>
  <div class="ui clearing divider"></div>
  <div class="ui cards">
        <div class="card">
          <div class="content">
            <span class="ui right corner label">
        <i class="warning red icon"></i>
      </span>
            <div class="header clickable">完成第一个子任务</div>
            <div class="note">50% 已完成
              应于 2018年5月29日 前完成</div>
            </div>
               
          <div class="ui bottom attached button" @click='logEvent()'><i class="add icon"></i> Log </div>
        </div>
        <div class="card">
          <div class="content">
            <div class="header">Veronika Ossi</div>
            <div class="description">Veronika Ossi是一名居住在纽约的舞美师，她喜爱小猫、音乐和派对。 </div>
          </div>
          <div class="ui bottom attached button"><i class="add icon"></i> Add Friend </div>
        </div>
        <div class="card">
          <span class="ui right corner label">
        <i class="warning red icon"></i>
      </span>
          <div class="content">
            <div class="header">多大事愛仕達水電第三方費艾克 Hess
              <div class="note">
              应于 2018年5月29日 前完成
            </div>
<div class="note">50% 已完成
            </div>
            
              <div class="note">5 個 Bug 尚待解決，共 10 個已知 Bug</div>
                <div class="note">来自项目：超级无敌建设
            </div>
            </div>
            <div class="description">Jenny是一名在该新学校学习媒体管理的在校生。 </div>
            <div class="ui feed">
  <div class="event">
    <div class="label">
      <img src="/images/avatar/small/laura.jpg">
    </div>
    <div class="content">
      <div class="summary">丫丫</div>
      <div class="extra text">我已完成該項目基礎建設，請繼續開發。 </div>
    </div>
  </div>
</div>
          </div>
          <div class="ui bottom attached button"><i class="add icon"></i> Add Friend </div>
        </div>
      </div>
</div>

<div class="ui segment">
  <a class="ui green ribbon label"> 现已完成 </a>
  <div class="ui clearing divider"></div>
  <div class="ui cards">
        <div class="card">
          <div class="content">
            <span class="ui right corner label">
        <i class="warning red icon"></i>
      </span>
            <div class="header clickable">完成第一个子任务</div>
            <div class="note">应于 2018年5月29日 前完成</div>
            <div class="note">现已完成 50%</div>
            </div>
             <div class="content">
            <div class="description ellipsis">帮助所有需要帮助所有需要的人完成他们的任务帮助所有需要的人完成他们的任务帮助所有需要的人完成他们的任务的人完成他们的任务 </div>
          </div>
          <div class="ui bottom attached button disabled"><i class="add icon"></i> Log </div>
        </div>
        <div class="card">
          <div class="content">
            <div class="header">Veronika Ossi</div>
            <div class="description">Veronika Ossi是一名居住在纽约的舞美师，她喜爱小猫、音乐和派对。 </div>
          </div>
          <div class="ui bottom attached button"><i class="add icon"></i> Add Friend </div>
        </div>
        <div class="card">
          <div class="content">
            <div class="header">Jenny Hess</div>
            <div class="description">Jenny是一名在该新学校学习媒体管理的在校生。 </div>
          </div>
          <div class="ui bottom attached button"><i class="add icon"></i> Add Friend </div>
        </div>
      </div>
</div>
      
      <div class="ui modal newLog" id='newLog'>
            <i class="close icon black"></i>
            <div class="header">
                New Log
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                开始时间
                            </label>
                            <input type="date" v-model="log.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="log.endDate">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="four wide field">
                            <label>
                                用时
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.timeCost">
                                <div class="ui basic label">小时 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                花费
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.economicCost">
                                <div class="ui basic label">元 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                完成度
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.completed">
                                <div class="ui basic label">% </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                分配于
                            </label>
                            <div class="ui fluid multiple search selection dropdown log_assign staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label>备注</label>
                        <textarea rows="6" maxlength="800" v-model="log.note"></textarea>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui red deny button">
                    取消
                </div>
                <div class="ui positive right button" @click="postLog">
                    Log
                </div>
            </div>
            <div v-bind:class="{ui:true, dimmer:true, inverted:true}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>
    </div2>
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
</style>
