<template>
  <div2>
  
    <div class="ui segment">
      <a class="ui blue ribbon label"> 尚未解决 </a>
      <button class="ui labeled icon button right floated" @click="submitBugEvent">
           <i class="plus icon"></i>提交新 Bug
      </button>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div class="card" v-for="_bug in bugs" :key="_bug.id" v-if="_bug.status != 'closed'">
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
            <div class="ui button disabled" v-else><i class="doctor icon"></i>修复中</div>
            <div class="ui button green" @click="resolveEvent"><i class="child icon"></i>解决</div>
          </div>
        </div>
      </div>
    </div>
    <div class="ui segment">
      <a class="ui green ribbon label"> 现已解决 </a>
      <div class="ui clearing divider"></div>
      <div class="ui cards">
        <div class="card" v-for="_bug in bugs" :key="_bug.id" v-if="_bug.status == 'closed'">
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
            <div class="ui button disabled"><i class="doctor icon"></i>已关闭</div>
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
        <div class="ui positive right button" @click="submitBug">
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
          <div class="four wide field">
            <label>分配于</label>
            <div class="ui fluid multiple search selection dropdown resolve_assign staffDropdown">
              <input type="hidden" name="receipt">
              <i class="dropdown icon"></i>
              <div class="default text"></div>
            </div>
          </div>
          <div class="field">
            <label>描述</label>
            <textarea rows="6" maxlength="800" v-model="resolve.description"></textarea>
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
        resolve: {}
      }
    },
    created() {
      this.getBugs()
    },
    methods: {
      getBugs() {
        this.$api.get(
          this.$apiUrl + '/statistics/bugs?uId=' + this.userId,
          null,
          data => {
            if (data.successful) {
              this.bugs = data.results
            } else {
              window.alert(data.information)
            }
          }
        )
      },
      resolveEvent: function() {
        $('.resolve')
          .modal({
            centered: true,
            blurring: false,
            inverted: false,
            closable: false,
            context: 'div2'
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
            context: 'div2'
          })
          .modal('show')
      },
      submitBug: function() {
        this.bug.projectID = $('.projectDropdown').dropdown('get value')
        this.bug.subtaskID = $('.subtaskDropdown').dropdown('get value')
        this.bug.status = 'Opened'
        this.bug.origin = $('.origin').dropdown('get value')
        this.bug.source = $('.source').dropdown('get value')
        this.bug.severity = $('.severity').dropdown('get value')
        this.bug.priority = $('.priority').dropdown('get value')
        this.bug.assignedID = 2
        this.$api.post(this.$apiUrl + '/projects/0/bugs',
          this.bug,
          data => {
            console.log(data.information)
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
