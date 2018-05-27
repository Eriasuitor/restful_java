<template>
  <div2>
  
    <div class="ui segment" v-show="subtasks.find(_ => _.completed < 100)">
      <a class="ui blue ribbon label"> 尚未完成 </a>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div class="card" v-for="_subtask in subtasks.filter(_ => _.completed < 100)" :key="_subtask.id">
          <span class="ui right corner label" v-if="_subtask.endDate - dateNow < 24 * 3600000">
                          <i class="warning red icon"></i>
                          </span>
          <div class="content">
            <div class="header">{{_subtask.name}}
              <div class="note">
                应于 {{$utils.formatDate(_subtask.endDate)}} 前完成
              </div>
              <div class="note">{{_subtask.completed}}% 已完成</div>
              <!-- <div class="note"># 個 Bug 尚待解決，共 # 個已知 Bug</div> -->
              <div class="note">来自项目：{{_subtask.project.name}}
              </div>
            </div>
            <div class="description">{{_subtask.description}}</div>
            <div class="ui feed" v-if="_subtask.log != null">
              <div class="event">
                <div class="label">
                  <img :src="_subtask.log.staff.image">
                </div>
                <div class="content">
                  <div class="summary">{{_subtask.log.staff.name}}</div>
                  <div class="extra text">{{_subtask.log.note}}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="ui bottom attached button" @click='logEvent(_subtask)'><i class="add icon"></i> Log </div>
        </div>
      </div>
    </div>
    <div class="ui segment" v-show="subtasks.find(_ => _.completed === 100)">
      <a class="ui green ribbon label"> 现已完成 </a>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div class="card" v-for="_subtask in subtasks.filter(_ => _.completed === 100)" :key="_subtask.id">
          <span class="ui right corner label" v-if="_subtask.endDate - dateNow < 24 * 3600000">
                          <i class="warning red icon"></i>
                          </span>
          <div class="content">
            <div class="header">{{_subtask.name}}
              <div class="note">
                应于 {{$utils.formatDate(_subtask.endDate)}} 前完成
              </div>
              <div class="note">{{_subtask.completed}}% 已完成</div>
              <!-- <div class="note"># 個 Bug 尚待解決，共 # 個已知 Bug</div> -->
              <div class="note">来自项目：{{_subtask.project.name}}
              </div>
            </div>
            <div class="description">{{_subtask.description}}</div>
            <div class="ui feed" v-if="_subtask.log != null">
              <div class="event">
                <div class="label">
                  <img :src="_subtask.log.staff.image">
                </div>
                <div class="content">
                  <div class="summary">{{_subtask.log.staff.name}}</div>
                  <div class="extra text">{{_subtask.log.note}}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="ui bottom attached button disabled"><i class="add icon"></i> Log </div>
        </div>
      </div>
    </div>
    <div class="ui segment" v-if="subtasks.length === 0">暂无分配于你的子任务</div>
  
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
            <div class="eight wide field required">
              <label>
                                                    用时
                                                </label>
              <div class="ui right labeled input">
                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.timeCost">
                <div class="ui basic label">小时 </div>
              </div>
            </div>
          </div>
          <div class="fields">
            <div class="eight wide field required">
              <label>
                                                                    完成度
                                                                </label>
              <div class="ui right labeled input">
                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.completed">
                <div class="ui basic label">% </div>
              </div>
            </div>
            <div class="eight wide field">
              <label>
                                                                    分配于
                                                                </label>
              <div class="ui fluid multiple search selection dropdown log_assign staffDropdown">
                <input type="hidden" name="receipt">
                <i class="dropdown icon"></i>
                <div class="default text"></div>
              </div>
            </div>
            <div class="eight wide field">
              <label>
                                                                    花费
                                                                </label>
              <div class="ui right labeled input">
                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.economicCost">
                <div class="ui basic label">元 </div>
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
        <div class="ui positive right button">
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
    data() {
      return {
        projects: [],
        userId: 2,
        project: {},
        isSaving: false,
        log: {},
        subtasks: [],
        dateNow: new Date().getTime(),
        logBelong: 0,
        userInfo: {},
        token: "",
        subtask: 0
      }
    },
    created() {
      window.localStorage.setItem('toLogin', this.$route.path)
      if (!window.localStorage.getItem('token') || !window.localStorage.getItem('userInfo')) {
        window.localStorage.removeItem('token')
        window.localStorage.removeItem('userInfo')
        this.$router.push({
          path: '/login',
          replace: true
        })
      } else {
        this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
        this.token = window.localStorage.getItem('token')
        this.getSubtasks()
      }
    },
    methods: {
      getSubtasks() {
        this.$api.get(
          this.$apiUrl + '/statistics/subtasks?token=' + this.token,
          null,
          data => {
            if (data.responseCode === 401) {
              window.localStorage.removeItem('token')
              window.localStorage.removeItem('userInfo')
              this.$router.push({
                path: '/login',
                replace: true
              })
            } else {
              if (data.successful) {
                this.subtasks = data.results
              } else {
                window.alert(data.information)
              }
            }
          }
        )
      },
      logEvent: function(_subtask) {
        this.log.subtaskID = _subtask.id
        this.subtask = _subtask
        $('.newLog')
          .modal({
            centered: true,
            blurring: false,
            inverted: false,
            closable: false,
            context: 'div2',
            onApprove: () => {
              return this.postLog()
            }
          })
          .modal('show')
      },
      postLog: function() {
        this.log.subtaskID = this.subtask.id
        this.log.assignedID = $('.dropdown.log_assign').dropdown('get value')
        if (!this.log.startDate) {
          window.alert('请填写开始时间');
          return false;
        }
  
        if (!this.log.endDate) {
          window.alert('请填写结束时间');
          return false;
        }
        if (this.log.startDate > this.log.endDate) {
          window.alert('结束时间不得早于开始时间');
          return false;
        }
        if (!this.log.timeCost) {
          window.alert('请填写耗时')
          return false
        }
        if (!this.log.completed) {
          window.alert('请填写完成度')
          return false
        }
  
        if (parseInt(this.log.completed) + this.subtask.completed > 100) {
          window.alert(`此次Log完成度最大值为 ${100 - this.subtask.completed}%`)
          return false
        }
        this.$api.post(
          this.$apiUrl + '/projects/' + 0 + '/logs?token=' + this.token,
          this.log,
          data => {
            if (data.responseCode === 401) {
              window.localStorage.removeItem('token')
              window.localStorage.removeItem('userInfo')
              this.$router.push({
                path: '/login',
                replace: true
              })
            } else {
              if (data.successful) {
                this.subtask.completed += parseInt(this.log.completed)
                this.getSubtasks()
              }
              window.alert(data.information)
            }
          }
        )
        return true
      },
      newProject: function() {
        //   this.isSaving = true
        this.project.managerID = $('.dropdown.manager').dropdown('get value')
        this.project.participantsList = []
        $('.dropdown.participants')
          .dropdown('get value')
          .split(',')
          .forEach(sId => {
            this.project.participantsList.push({
              staffId: sId
            })
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
      refreshProjects: function() {
        this.$api.get(
          this.$apiUrl + '/projects?staffid=' + this.userId,
          null,
          data => {
            this.projects = data.projectNames
          }
        )
      },
      newProjectEvent: function() {
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
      empty: function() {
        $('.dropdown.participants').dropdown('clear')
      },
      search: function() {
        $('.ui.search').search({
          apiSettings: {
            onResponse: resp => {
              $.each(resp.projectNames, function(index, item) {
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
      detail: function(pId) {
        this.$router.push({
          path: '/projects/' + pId,
          replace: true
        })
      },
  
    },
    mounted() {
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
  
  .clickable:hover {
    cursor: pointer;
  }
  
  .note {
    font-size: 0.5em;
  }
</style>
