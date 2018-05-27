<template>
  <div2>
  
    <div class="ui segment">
      <a class="ui blue ribbon label"> 分配于我 </a>
      <button class="ui labeled icon button right floated" @click="submitBugEvent">
                                 <i class="plus icon"></i>提交新 Bug
                            </button>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div v-if="!bugs.find(_ => _.assignedID === userInfo.id && _.insertUser != userInfo.id && _.status != 'Resolved')">暂无数据</div>
        <div class="card" v-for="_bug in bugs.filter(_ => _.assignedID === userInfo.id && _.insertUser != userInfo.id  && _.status != 'Resolved' )" :key="_bug.id">
          <!-- <span class="ui right corner label">
                                      <i class="warning red icon"></i>
                                    </span> -->
          <div class="content">
            <div class="header">{{_bug.name}}
              <div class="note" v-if="_bug.project != null">来自项目：{{_bug.project.name}}
              </div>
              <div class="note" v-if="_bug.subtask != null">来自子任务：{{_bug.subtask.name}}
              </div>
              <div class="note" v-if="_bug.assignedStaff != null">分配于：{{_bug.assignedStaff.name}}
              </div>
            </div>
            <div class="description">{{_bug.description}}</div>
            <div class="ui feed" v-if="_bug.lasEditStaff != null">
              <div class="event">
                <div class="label">
                  <img :src="_bug.lasEditStaff.image">
                </div>
                <div class="content">
                  <div class="summary">{{_bug.lasEditStaff.name}}</div>
                  <div class="extra text">{{_bug.note}}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="ui bottom attached buttons">
            <div class="ui button" v-if="_bug.assignedId === userId"><i class="add icon"></i> Close </div>
            <div class="ui button" v-else-if="_bug.status === 'created'"><i class="add icon"></i>打开</div>
            <!-- <div class="ui button disabled" v-else><i class="doctor icon"></i>修复中</div> -->
            <div class="ui animated fade button " @click="modifyStatus(_bug)" tabindex="0" v-show="_bug.status === 'Created' || _bug.status === 'Recreated'">
              <div class="visible content"><i class="bug icon"></i>{{_bug.status}}</div>
              <div class="hidden content">打开</div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div class="ui animated fade button disabled" @click="modifyStatus(_bug)" tabindex="0" v-show="_bug.status === 'Processing'">
              <div class="visible content"><i class="doctor icon"></i>{{_bug.status}}</div>
              <div class="hidden content"></div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div @click="modifyStatus(_bug)" :class="['ui', 'animated', 'fade', 'button', {'disabled': _bug.insertUser != userId.id}]" tabindex="0" v-show="_bug.status === 'Resolved'">
              <div class="visible content"><i class="checkmark icon"></i>{{_bug.status}}</div>
              <div class="hidden content">重新创建</div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div :class="['ui', 'button', 'green',{'disabled': _bug.status != 'Processing'}]" @click="resolveEvent(_bug)"><i class="child icon"></i>解决</div>
          </div>
        </div>
      </div>
    </div>
    <div class="ui segment">
      <a class="ui green ribbon label"> 我提交的 </a>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div v-if="!bugs.find(_ => _.insertUser === userInfo.id )">暂无数据</div>
        <div class="card" v-for="_bug in bugs.filter(_ => _.insertUser === userInfo.id )" :key="_bug.id">
          <!-- <span class="ui right corner label">
                                      <i class="warning red icon"></i>
                                    </span> -->
          <div class="content">
            <div class="header">{{_bug.name}}
              <div class="note" v-if="_bug.project != null">来自项目：{{_bug.project.name}}
              </div>
              <div class="note" v-if="_bug.subtask != null">来自子任务：{{_bug.subtask.name}}
              </div>
              <div class="note" v-if="_bug.assignedStaff != null">分配于：{{_bug.assignedStaff.name}}
              </div>
            </div>
            <div class="description">{{_bug.description}}</div>
            <div class="ui feed" v-if="_bug.lasEditStaff != null">
              <div class="event">
                <div class="label">
                  <img :src="_bug.lasEditStaff.image">
                </div>
                <div class="content">
                  <div class="summary">{{_bug.lasEditStaff.name}}</div>
                  <div class="extra text">{{_bug.note}}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="ui bottom attached buttons">
            <div class="ui button" v-if="_bug.assignedId === userId"><i class="add icon"></i> Close </div>
            <div class="ui button" v-else-if="_bug.status === 'created'"><i class="add icon"></i>打开</div>
            <!-- <div class="ui button disabled" v-else><i class="doctor icon"></i>修复中</div> -->
            <div class="ui animated fade button " @click="modifyStatus(_bug)" tabindex="0" v-show="_bug.status === 'Created' || _bug.status === 'Recreated'">
              <div class="visible content"><i class="bug icon"></i>{{_bug.status}}</div>
              <div class="hidden content">打开</div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div class="ui animated fade button disabled" @click="modifyStatus(_bug)" tabindex="0" v-show="_bug.status === 'Processing'">
              <div class="visible content"><i class="doctor icon"></i>{{_bug.status}}</div>
              <div class="hidden content"></div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div @click="modifyStatus(_bug)" :class="['ui', 'animated', 'fade', 'button']" tabindex="0" v-show="_bug.status === 'Resolved'">
              <div class="visible content"><i class="checkmark icon"></i>{{_bug.status}}</div>
              <div class="hidden content">重新创建</div>
              <!-- <i class="doctor icon"></i> -->
            </div>
            <div :class="['ui', 'button', 'green',{'disabled': _bug.status != 'Processing'}]" @click="resolveEvent(_bug)"><i class="child icon"></i>解决</div>
          </div>
        </div>
      </div>
    </div>
  
    <div class="ui modal newBug" id='newBug'>
      <i class="close icon black"></i>
      <div class="header">
        提交新 Bug
      </div>
      <div class="content">
        <form class="ui form">
          <div class="field required">
            <label>名称</label>
            <input type="text" v-model="bug.name">
          </div>
  
          <div class="field">
            <label>描述</label>
            <textarea rows="6" maxlength="800" v-model="bug.description"></textarea>
          </div>
          <div class="fields">
            <div class="eight wide field required">
              <label>所属项目</label>
              <div class="ui fluid multiple search selection dropdown projectDropdown">
                <input type="hidden" name="receipt">
                <i class="dropdown icon"></i>
                <div class="default text">点击选择</div>
              </div>
            </div>
            <div class="eight wide field required" @click="subtaskDropdown()">
              <label>所属子任务</label>
              <div class="ui fluid multiple search selection dropdown subtaskDropdown">
                <input type="hidden" name="subtask">
                <i class="dropdown icon"></i>
                <div class="default text">点击选择</div>
              </div>
            </div>
          </div>
          <div class="fields">
            <div class="four wide field">
              <label>源于</label>
              <div class="ui fluid selection dropdown source defaultDropdown">
                <input type="hidden" name="phase">
                <div class="default text">编码</div>
                <div class="menu">
                  <div class="item" value="需求">需求</div>
                  <div class="item" value="架构">架构</div>
                  <div class="item" value="设计">设计</div>
                  <div class="item" value="编码">编码</div>
                  <div class="item" value="测试">测试</div>
                  <div class="item" value="集成">集成</div>
                </div>
              </div>
            </div>
            <div class="four wide field">
              <label>发现于</label>
              <div class="ui fluid selection dropdown origin defaultDropdown">
                <input type="hidden" name="phase">
                <div class="default text">测试阶段</div>
                <div class="menu">
                  <div class="item" value="需求阶段">需求阶段</div>
                  <div class="item" value="架构阶段">架构阶段</div>
                  <div class="item" value="设计阶段">设计阶段</div>
                  <div class="item" value="编码阶段">编码阶段</div>
                  <div class="item" value="测试阶段">测试阶段</div>
                </div>
              </div>
            </div>
            <div class="four wide field">
              <label>严重程度</label>
              <div class="ui fluid selection dropdown severity defaultDropdown">
                <input type="hidden" name="phase">
                <div class="default text">Major</div>
                <div class="menu">
                  <div class="item" value="Critical">Critical</div>
                  <div class="item" value="Major">Major</div>
                  <div class="item" value="Minor">Minor</div>
                  <div class="item" value="Cosmetic">Cosmetic</div>
                  <div class="item" value="Other">Other</div>
                </div>
              </div>
            </div>
            <div class="four wide field">
              <label>优先级</label>
              <div class="ui fluid selection dropdown priority defaultDropdown">
                <input type="hidden" name="phase">
                <div class="default text">Normal</div>
                <div class="menu">
                  <div class="item" value="Immediately">Immediately</div>
                  <div class="item" value="Normal">Normal</div>
                  <div class="item" value="Not Urgent">Not Urgent</div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="actions">
        <div class="ui red deny button">
          取消
        </div>
        <div class="ui positive right button">
          提交
        </div>
      </div>
      <div v-bind:class="{ui:true, dimmer:true, inverted:true}">
        <div class="ui text loader ">保存中</div>
      </div>
    </div>
  
    <div class="ui modal resolve" id='resolve'>
      <i class="close icon black"></i>
      <div class="header">
        解决 Bug
      </div>
      <div class="content">
        <form class="ui form">
          <!-- <div class="four wide field">
              <label>分配于</label>
              <div class="ui fluid multiple search selection dropdown resolve_assign staffDropdown">
                <input type="hidden" name="receipt">
                <i class="dropdown icon"></i>
                <div class="default text"></div>
              </div>
            </div> -->
          <div class="field">
            <label>描述</label>
            <textarea rows="6" maxlength="800" v-model="resolve.note"></textarea>
          </div>
        </form>
      </div>
      <div class="actions">
        <div class="ui red deny button">
          取消
        </div>
        <div class="ui positive right button">
          提交
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
        subtasks: [],
        bug: {},
        projects: [],
        userId: 2,
        project: {},
        isSaving: false,
        log: {},
        bugs: [],
        resolve: {},
        userInfo: {},
        token: ""
      }
    },
    created() {
      window.localStorage.setItem('toLogin', this.$route.path)
      console.log(window.localStorage.getItem('token'))
      console.log(window.localStorage.getItem('userInfo'))
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
        this.getBugs()
      }
    },
    methods: {
      modifyStatus: function(_bug) {
        if (window.confirm('你确定要改变此Bug的状态吗？')) {
          let bugToPut = {
            id: _bug.id,
            note: _bug.note,
            status: _bug.status
          }
          this.$api.put(
            this.$apiUrl + `/projects/${_bug.project.id}/bugs/${_bug.id}?token=${this.token}`,
            bugToPut,
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
                  for (let _ of this.bugs) {
                    // if(_.id = _bug.id){
                    //    _.status =  data.object.status
                    //    _.note =  data.object.note
                    //    _.lasEditStaff =  data.object.lasEditStaff
                    //    console.log(_)
                    //    break;
                    // }
                    this.getBugs()
                  }
                }
                window.alert(data.information)
              }
            }
          )
        }
      },
      getBugs() {
        this.$api.get(
          this.$apiUrl + '/statistics/bugs?token=' + this.token,
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
                this.bugs = data.results
              } else {
                window.alert(data.information)
              }
            }
          }
        )
      },
      resolveBug: function(_bug) {
        this.resolve.id = _bug.id
        this.resolve.status = _bug.status
        if (!this.resolve.note) {
          window.alert('请填写说明信息.')
          return false
        }
        this.$api.put(
          this.$apiUrl + `/projects/${_bug.project.id}/bugs/${_bug.id}?token=${this.token}`,
          this.resolve,
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
                this.getBugs()
              }
              window.alert(data.information)
            }
          }
        )
      },
      resolveEvent: function(_bug) {
        $('.resolve')
          .modal({
            centered: true,
            blurring: false,
            inverted: false,
            closable: false,
            context: 'div2',
            onApprove: () => {
              return this.resolveBug(_bug)
            }
          })
          .modal('show')
      },
      submitBugEvent: function() {
        $('.newBug')
          .modal({
            centered: true,
            blurring: false,
            inverted: false,
            closable: false,
            context: 'div2',
            onApprove: () => {
              return this.submitBug()
            }
          })
          .modal('show')
      },
      submitBug: function() {
        this.bug.projectID = $('.projectDropdown').dropdown('get value')
        this.bug.subtaskID = $('.subtaskDropdown').dropdown('get value')
        this.bug.source = $('.source').dropdown('get value')
        this.bug.origin = $('.origin').dropdown('get value')
        this.bug.severity = $('.severity').dropdown('get value')
        this.bug.priority = $('.priority').dropdown('get value')
        this.bug.source = this.bug.source ? this.bug.source : '编码'
        this.bug.origin = this.bug.source ? this.bug.source : '测试阶段'
        this.bug.severity = this.bug.source ? this.bug.source : 'Major'
        this.bug.priority = this.bug.source ? this.bug.source : 'Normal'
  
        if (!this.bug.name || !this.bug.name.trim()) {
          window.alert('请填写Bug名称.')
          return false
        }
        if (!this.bug.projectID) {
          window.alert('请选择所属项目.')
          return false
        }
        if (!this.bug.subtaskID) {
          window.alert('请选择所属子任务.')
          return false
        }
        if (!this.bug.source || !this.bug.source.trim()) {
          window.alert('源于 为非空字段.')
          return false
        }
        if (!this.bug.origin || !this.bug.origin.trim()) {
          window.alert('发现于 为非空字段.')
          return false
        }
        if (!this.bug.severity || !this.bug.severity.trim()) {
          window.alert('严重程度 为非空字段.')
          return false
        }
        if (!this.bug.priority || !this.bug.priority.trim()) {
          window.alert('优先级 为非空字段.')
          return false
        }
        this.$api.post(this.$apiUrl + '/projects/0/bugs?token=' + this.token,
          this.bug,
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
                this.getBugs()
              }
              window.alert(data.information)
            }
          })
      },
      subtaskDropdown: function() {
        $('.subtaskDropdown').dropdown({
          apiSettings: {
            onResponse: resp => {
              let subtasks = []
              resp.phaseList.forEach(element => {
                element.subtaskList.forEach(item => subtasks.push({
                  value: item.id,
                  name: item.name
                }))
              })
              resp.results = subtasks
              return resp
            },
            url: this.$apiUrl + '/projects/' + $('.projectDropdown').dropdown('get value') + '/phases?q=11{query}'
          },
          maxSelections: 1
        })
      }
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
      $('.projectDropdown').dropdown({
        apiSettings: {
          onResponse: resp => {
            resp.results = resp.projectNames;
            $.each(resp.projectNames, function(index, item) {
              item.value = item.id
            })
            return resp
          },
          url: this.$apiUrl + '/projects?q={query}&token=' + window.localStorage.getItem('token')
        },
        maxSelections: 1
      })
      $('.subtaskDropdown').dropdown({
        apiSettings: {
          onResponse: resp => {
            let subtasks = []
            resp.phaseList.forEach(element => {
              element.subtaskList.forEach(item => subtasks.push({
                value: item.id,
                name: item.name
              }))
            })
            resp.results = subtasks
            return resp
          },
          url: null
        },
        maxSelections: 1
      })
      $('.defaultDropdown').dropdown()
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
