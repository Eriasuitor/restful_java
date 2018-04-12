<template>
    <div2>

        <div class="ui breadcrumb">
            <a class="section">JIRA</a>
            <div class="divider"> / </div>
            <a class="section" @click="routeTo('/projects')">Projects</a>
            <div class="divider"> / </div>
            <div class="active section">{{project.name}}</div>
        </div>

        <!-- <div class="ui dropdown button" @click="intervalSet()">
<span class="text">Choose Category</span>
<i class="dropdown icon"></i>
<div class="menu">
<div class="item">
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
    <span class="text">1 天</span>
</div>
</div>
</div> -->
        <!-- <button class="ui labeled icon button right floated" @click="newPhaseEvent">
<i class="plus icon"></i>新建阶段任务
</button> -->
        <div class="ui image icon right floated">
            <i class="circular edit outline link icon" @click="editProjectEvent()"></i>
            <i class="circular delete link icon"></i>
        </div>
        <div class="ui segment testNote">
            <div class="ui button testButton" @click="testMt()">Show flowing popup</div>
            <div class="ui flowing popup top left transition hidden ">
                <div class="ui three column divided center aligned grid">
                    <div class="column">
                        <h4 class="ui header">Basic Plan</h4>
                        <p>
                            <b>2</b> projects, $10 a month</p>
                        <div class="ui button">Choose</div>
                    </div>
                    <div class="column">
                        <h4 class="ui header">Business Plan</h4>
                        <p>
                            <b>5</b> projects, $20 a month</p>
                        <div class="ui button">Choose</div>
                    </div>
                    <div class="column">
                        <h4 class="ui header">Premium Plan</h4>
                        <p>
                            <b>8</b> projects, $25 a month</p>
                        <div class="ui button">Choose</div>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="ui dividing header">{{project.name}}
            <div class="sub header">{{project.description}}</div>
        </h3>

        <div class="ui secondary segment">

            <div class="ui">
                <div class="ui blue image label"><img :src="project.staff.image"> {{project.staff.name}}
                    <div class="detail">负责人</div>
                </div>
                <i class="small circular edit outline link icon editManager popupTriger"></i>
                <div class="ui flowing popup top left transition hidden">
                    <div class="ui divided center aligned grid">
                        <div class="column">
                            <div>
                                负责人
                                <div class="ui flowing multiple search selection dropdown edit_manger staffDropdown">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                                <div class="ui button" @click="modifyManager()">保存</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <i class="small circular edit outline link icon" @click="modifyManagerEvent()"></i>

            <p>日期：{{formatDate(project.startDate)}} 至 {{formatDate(project.endDate)}} 代码库：{{project.url}}</p>

            <div class="ui">
                <div class="ui statistics">
                    <div class="statistic">
                        <div class="value">
                            {{project.completed}}%
                        </div>
                        <div class="label">完成</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{project.timeCost}}</div>
                        <div class="label">时间花费</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{project.economicCost}}元</div>
                        <div class="label">费用产生</div>
                    </div>
                    <div class="statistic">
                        <div class="value">#</div>
                        <div class="label">参与人数</div>
                    </div>
                </div>
                <div class="ui image label" v-for="staff in project.staffList" v-if="staff.id != project.managerID" :key="'staff' + staff.id" @click="removeParticipant(staff.id)"><img :src="staff.image"> {{staff.name}}
                    <i class="delete icon"></i>
                </div>
                <i class="small circular add link icon addParticipant popupTriger"></i>
                <div class="ui flowing popup top left transition hidden">
                    <div class="ui divided center aligned grid">
                        <div class="column">
                            <div>
                                姓名
                                <div class="ui flowing multiple search selection dropdown add_participant staffDropdown">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                                <div class="ui button" @click="addParticipant()">添加</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <i class="small circular plus link icon" @click="newPhaseEvent"></i>新建阶段任务
        <div class="scrollY">
            <table class="ui compact red table">
                <thead>
                    <tr>
                        <th class="two wide">阶段</th>
                        <th class="three wide">子任务</th>
                        <th class="four wide">完成度</th>
                        <th class="seven wide" id='temporalPlanning' ref='temporalPlanning'>时间规划</th>
                    </tr>
                </thead>
                <tbody>
                    <template v-for="phase in phases">
                        <tr :key="'pha' + phase.id">
                            <td :rowspan="phase.subtaskList.length + 2">{{phase.name}}{{phase.subtaskList.length}}
                                <!-- <div class="ui fade animated button" @click="newSubtaskEvent(phase.id, phase.name)">
                    <div class="hidden content">子任务</div>
                    <div class="visible content">
                        <i class="plus icon"></i>
                    </div>
                </div> -->
                                <!-- <i class="small circular plus link icon" @click="newSubtaskEvent(phase.id, phase.name)"></i> -->
                                <i class="small circular edit outline link icon"></i>
                                <!-- <div class="ui fade animated button" @click="newSubtaskEvent(phase.id, phase.name)">
                    <div class="hidden content">编辑</div>
                    <div class="visible content">
                        <i class="edit icon"></i>
                    </div>
                </div> -->
                            </td>
                        </tr>
                        <tr v-for="subtask in phase.subtaskList" :key="'sub' + subtask.id">
                            <td>
                                <a class="link" @click="routeTo(project.id + '/subtasks/' + subtask.id)">{{subtask.name}}</a>
                            </td>
                            <td>
                                <div class="ui progress" :id="'pro' + subtask.id">
                                    <div class="bar">
                                        <div class="progress"></div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <a v-for="(item, index) in dateList" :key="subtask.id + index" :title="'开始于' + formatDate(subtask.startDate) + '，结束于' + formatDate(subtask.endDate)" :class="{ 'ui': true, 'circular': true, 'label': true,'green':subtask.completed === 100, 'red': item >= new Date(subtask.startDate) && (item <= new Date(subtask.endDate) && setRed(subtask.id) || redMap[subtask.id] == undefined && setRed(subtask.id))}">{{item.getMonth() + '.' + item.getDate()}}</a>
                            </td>
                        </tr>
                        <tr :key="'subAdd' + phase.id">
                            <td>
                                <i class="small circular plus link icon" @click="newSubtaskEvent(phase.id, phase.name)"></i>
                            </td>
                        </tr>
                    </template>
                </tbody>
            </table>

            <!-- <table class="ui collapsing celled structured small compact single line table ">
<thead>
    <tr>
        <th rowspan="2">阶段</th>
        <th rowspan="2" class="subtask">子任务</th>
        <th rowspan="2" class="complete">完成度</th>
        <th rowspan="2" v-for="(item,index) in dates()" :key="index">
            <div class="ui mini horizontal statistic red" v-show="item.getDate() / iterval < 1 || index === 0">
                <div class="value">
                    {{item.getMonth()}}-
                </div>
            </div>
            <span></span>
            {{item.getDate()}}
        </th>
    </tr>
</thead>
<tbody>
    <template v-for="phase in phases">
        <tr :key="phase.id">
            <td :rowspan="phase.subtaskList.length + 1">{{phase.name}}{{phase.subtaskList.length}}<br>
                <div class="ui fade animated button" @click="newSubtaskEvent(phase.id, phase.phaseNum)">
                    <div class="hidden content">子任务</div>
                    <div class="visible content">
                        <i class="plus icon"></i>
                    </div>
                </div>
            </td>
        </tr>
        <tr v-for="subtask in phase.subtaskList" :key="subtask.id">
            <td>
                <a class="link" v-bind:href="project.id + '/subtasks/' + subtask.id">{{subtask.name}}</a>
            </td>

            <td v-for="(item,index) in dates()" :key="index" class="center aligned">
                <i class="large green checkmark icon"></i>
            </td>
        </tr>
    </template>
</tbody>
</table> -->
            <!-- 456{{endDate}}
<div class="ui animated fade button" tabindex="0">
<div class="visible content">&nbsp;</div>
<div class="hidden content">
    <i class="pencil alternate icon"></i>
</div>
</div>

<div>{{endDate.getDate()}}</div> -->

            <!-- <div v-for="(item,index) in dates()" :key="index">
<div class="ui mini label"></div>
<span v-if="item.getDate() === 1 || index === 0">{{item.getMonth()}}</span>-
{{item.getDate()}}
</div> -->
            <!-- <div class="ui small progress" id='test' @click="progress('test', 50)">
<div class="bar">
    <div class="progress"></div>
</div>
</div> -->
        </div>
        <div class="ui modal phase" id='phase'>
            <i class="close icon black"></i>
            <div class="header">
                新建阶段任务
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field">
                        <label>阶段任务</label>
                        <input type="text" v-model="phase.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="phase.description"></textarea>
                    </div>

                    <div class="fields">
                        <div class="eight wide field" @click="showDropdown('phase_manger', 1)">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown phase_manger">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field">
                            <label>
                                开始时间
                            </label>
                            <input type="date" v-model="phase.startDate">
                        </div>
                        <div class="eight wide field">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="phase.endDate">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button" @click="newPhase">
                    新建
                </div>
            </div>
        </div>
        <div class="ui modal subtask" id='subtask'>
            <i class="close icon black"></i>
            <div class="header">
                新增子任务
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field">
                        <label>任务</label>
                        <input type="text" v-model="subtask.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="subtask.description"></textarea>
                    </div>

                    <div class="fields">
                        <div class="eight wide field" @click="showDropdown('subtask_manger',1)">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown subtask_manger">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field" @click="showDropdown('subtask_assign', 1)">
                            <label>
                                分配于
                            </label>
                            <div class="ui fluid multiple search selection dropdown subtask_assign">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">

                        <div class="eight wide field" @click="showDropdownLocal('subtask_phase')">
                            <label>
                                阶段
                            </label>
                            <div class="ui fluid selection search dropdown subtask_phase">
                                <input type="hidden" name="phase">
                                <div class="default text">{{phaseName}}</div>
                                <div class="menu">
                                    <div v-for="phase in phases" :key="'optPhase' + phase.id" class="item" :data-value="phase.id">{{phase.name}}</div>
                                </div>
                            </div>
                        </div>
                        <div class="eight wide field">
                            <label>
                                预计用时
                            </label>
                            <div class="ui right labeled input">
                                <input type="number" step="0.5" min="0" v-model="subtask.requiredTime">
                                <div class="ui basic label">小时 </div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field">
                            <label>
                                开始时间
                            </label>
                            <input type="date" v-model="subtask.startDate">
                        </div>
                        <div class="eight wide field">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="subtask.endDate">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button" @click="addSubtask">
                    新增
                </div>
            </div>
        </div>
        <!-- <project-modal :project='project' @save='putProject'></project-modal> -->
        <div class="ui modal editProject">
            <i class="close icon black"></i>
            <div class="header">
                更改项目
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>项目名称</label>
                        <input type="text" v-model="projectToPut.name">{{projectToPut}}
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="projectToPut.description"></textarea>
                    </div>

                    <div class="fields">

                        <div class="four wide field required">
                            <label>
                                开始时间
                            </label>
                            <input type="date" :value="startDate" ref='projectStartDate'>{{startDate}}
                        </div>
                        <div class="four wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" :value="endDate" ref='projectEndDate'>
                        </div>
                        <div class="eight wide field">
                            <label>
                                代码库地址
                            </label>
                            <input type="url" v-model="projectToPut.url">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button" @click="save()">
                    更改
                </div>
            </div>
            <div v-bind:class="{ui:true, dimmer:true, inverted:true, active:isSaving}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>

        <!-- <input type="date" v-model="test">{{test}}{{typeof(test)}} -->
        <input type="date" :value="test">
        <input v-model="projectToPut.name">

        <div class="ui menu">
            <div class="ui button bt1" @click="testMt()">Show flowing popup</div>
            <div class="ui flowing popup top left transition hidden">
                <div class="ui three column divided center aligned grid">
                    <div class="column">
                        <h4 class="ui header">Basic Plan</h4>
                        <p>
                            <b>2</b> projects, $10 a month</p>
                        <div class="ui button">Choose</div>
                    </div>
                </div>
            </div>
        </div>
    </div2>
</template>
<script>
import ProjectModal from '../components/ProjectModal'
export default {
  components: { ProjectModal },
  data () {
    return {
      sq: null,
      iterval: 5,
      userId: 2,
      projectToPut: { startDate: null, endDate: null },
      phases: {},
      phase: {},
      subtask: {},
      sub_phaseId: 0,
      phaseName: 0,
      project: { staff: {} },
      dateNum: 0,
      dateList: [],
      redMap: {},
      test: '2018-04-04',
      isSaving: false
    }
  },
  created () {
    this.refreshPhases()
    if (this.project.id === undefined) {
      this.$api.get(
        this.$apiUrl + '/projects/' + this.$route.params.id,
        null,
        data => {
          this.project = data
          this.getDateList()
        }
      )
    } else {
      this.getDateList()
    }
  },
  computed: {
    startDate: function () {
      var date = new Date(this.projectToPut.startDate)
      console.log(this.projectToPut.startDate)
      console.log(
        date.getFullYear() +
          '-' +
          (date.getMonth() < 9
            ? '0' + (date.getMonth() + 1)
            : date.getMonth() + 1) +
          '-' +
          (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
      )
      return (
        date.getFullYear() +
        '-' +
        (date.getMonth() < 9
          ? '0' + (date.getMonth() + 1)
          : date.getMonth() + 1) +
        '-' +
        (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
      )
    },
    endDate: function () {
      var date = new Date(this.projectToPut.endDate)
      return (
        date.getFullYear() +
        '-' +
        (date.getMonth() < 9
          ? '0' + (date.getMonth() + 1)
          : date.getMonth() + 1) +
        '-' +
        (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
      )
    }
  },
  methods: {
    modifyManagerEvent: function () {
      window.alert('Modify')
    },
    addParticipantEvent: function () {
      window.alert('add')
    },
    removeParticipant: function (staffId) {
      console.log(
        this.$apiUrl +
          '/projects/' +
          this.$route.params.id +
          '/participants?sId=' +
          staffId
      )
      this.$api.delete(
        this.$apiUrl +
          '/projects/' +
          this.$route.params.id +
          '/participants?sId=' +
          staffId,
        null,
        data => {
          window.alert(data.information)
          if (data.successful) {
            for (var i = 0; i < this.project.staffList.length; i++) {
              if (this.project.staffList[i].id === staffId) {
                this.project.staffList.splice(i, 1)
              }
            }
          }
        }
      )
    },
    modifyManager: function () {
      var projectToPut = {}
      projectToPut.managerID = $('.dropdown.edit_manger').dropdown(
        'get value')
      projectToPut.id = this.project.id
      projectToPut.lastEditUser = 'VUE'
      this.$api.put(
        this.$apiUrl + '/projects/' + this.project.id + '/manager',
        projectToPut,
        data => {
          if (data.successful) {
            this.project.managerID = data.project.managerID
            this.project.staff = data.project.staff
          }
          window.alert(data.information)
        }
      )
    },
    addParticipant: function () {
      var participant = {}
      participant.projectId = this.project.id
      participant.staffId = $('.dropdown.add_participant').dropdown(
        'get value')
      participant.role = 'Developer'
      participant.insertUser = 1
      participant.lastEditUser = 1
      this.$api.post(
        this.$apiUrl + '/projects/' + this.project.id + '/participants',
        participant,
        data => {
          if (data.successful) {
            this.project.staffList = data.results
          }
          window.alert(data.information)
        }
      )
    },
    save: function () {
      this.projectToPut.startDate = this.$refs.projectStartDate.value
      this.projectToPut.endDate = this.$refs.projectEndDate.value

      this.projectToPut.insertUser = 1
      this.projectToPut.lastEditUser = 1
      console.log(this.projectToPut)
      this.$api.put(
        this.$apiUrl + '/projects/' + this.project.id,
        this.projectToPut,
        data => {
          if (data.successful) {
            this.project.id = this.projectToPut.id
            this.project.name = this.projectToPut.name
            this.project.description = this.projectToPut.description
            this.project.url = this.projectToPut.url
            this.project.startDate = new Date(this.projectToPut.startDate)
            this.project.endDate = new Date(this.projectToPut.endDate)
          }
          window.alert(data.information)
        }
      )
    },
    editProjectEvent: function () {
      this.projectToPut.id = this.project.id
      this.projectToPut.name = this.project.name
      this.projectToPut.description = this.project.description
      this.projectToPut.url = this.project.url
      this.projectToPut.startDate = this.project.startDate
      this.projectToPut.endDate = this.project.endDate
      console.log(this.projectToPut)
      $('.editProject')
        .modal({
          centered: true,
          blurring: true,
          inverted: false,
          closable: false,
          context: 'div2'
        })
        .modal('show')
    },
    putProject: function (payload) {
      console.log(payload)
    },
    setRed: function (key) {
      this.redMap[key] = 1
      return true
    },
    getDateList: function () {
      var gapDays = Math.ceil(
        (new Date(this.project.endDate).getTime() -
          new Date(this.project.startDate).getTime()) /
          86400000
      )
      var gapDayBall = Math.ceil(gapDays / (this.dateNum - 1))
      for (var i = 0; i < this.dateNum - 1; i++) {
        this.dateList.push(
          new Date(
            new Date(this.project.startDate).getTime() +
              gapDayBall * i * 86400000
          )
        )
      }
      this.dateList.push(new Date(this.project.endDate))
      //   //   var datesRet = []
      //   while (opeDate < this.endDate) {
      //     datesRet.push(opeDate)
      //     opeDate = new Date(opeDate.setDate(opeDate.getDate() + this.iterval))
      //   }
      //   return datesRet
    },
    refreshProgressBar: function () {
      for (var i = 0; i < this.phases.length; i++) {
        for (var j = 0; j < this.phases[i].subtaskList.length; j++) {
          $('#pro' + this.phases[i].subtaskList[j].id).progress({
            percent: this.phases[i].subtaskList[j].completed
          })
        }
      }
    },
    refreshPhases: function () {
      this.$api.get(
        this.$apiUrl +
          '/projects/' +
          this.$route.params.id +
          '/phases?staffid=' +
          this.userId,
        null,
        data => {
          this.phases = data.phaseList
          if (this.phases != null) {
            setTimeout(() => {
              this.refreshProgressBar()
            }, 20)
          }
        }
      )
    },
    routeTo: function (path) {
      window.localStorage.setItem('project', JSON.stringify(this.project))
      this.$router.push(path)
    },
    gap: function*() {
      var opeDate = this.startDate
      while (this.opeDate < this.endDate) {
        yield this.opeDate
        opeDate.setDate(opeDate.getDate() + 1)
      }
    },
    dates: function () {
      var opeDate = this.startDate
      var datesRet = []
      while (opeDate < this.endDate) {
        datesRet.push(opeDate)
        opeDate = new Date(opeDate.setDate(opeDate.getDate() + this.iterval))
      }
      return datesRet
    },
    newPhase: function () {
      this.phase.managerID = $('.dropdown.phase_manger').dropdown('get value')
      this.$api.post(
        this.$apiUrl +
          '/projects/' +
          this.$route.params.id +
          '/phases?staffid=' +
          this.userId,
        this.phase,
        data => {
          if (data.successful) {
            this.refreshPhases()
          }
          window.alert(data.information)
        }
      )
    },
    addSubtask: function () {
      this.subtask.managerID = $('.dropdown.subtask_manger').dropdown(
        'get value'
      )
      this.subtask.assignedID = $('.dropdown.subtask_assign').dropdown(
        'get value'
      )
      this.subtask.phaseID = $('.dropdown.subtask_phase').dropdown('get value')
      if (this.subtask.phaseID === '') this.subtask.phaseID = this.sub_phaseId
      this.subtask.insertUser = 'VUE'
      this.subtask.lastEditUser = 'VUE'
      this.subtask.status = 'Processing'
      this.$api.post(
        this.$apiUrl +
          '/projects/' +
          this.$route.params.id +
          '/subtasks?staffid=' +
          this.userId,
        this.subtask,
        data => {
          if (data.successful) {
            this.refreshPhases()
          }
          window.alert(data.information)
        }
      )
    },
    newPhaseEvent: function () {
      $('.phase')
        .modal({
          centered: true,
          blurring: true,
          inverted: false,
          closable: false,
          context: 'div2'
        })
        .modal('show')
    },
    newSubtaskEvent: function (phaseId, subPhaseName) {
      this.sub_phaseId = phaseId
      this.phaseName = subPhaseName
      $('.subtask')
        .modal({
          centered: true,
          blurring: true,
          inverted: false,
          closable: false,
          context: 'div2'
        })
        .modal('show')
    },
    showDropdownLocal: function (className) {
      $('.' + className).dropdown()
    },
    showDropdown: function (className, maxSelections) {
      $('.' + className).dropdown({
        apiSettings: {
          url: this.$apiUrl + '/staff?name={query}'
        },
        maxSelections: maxSelections
      })
    },
    intervalSet: function () {
      $('.ui.dropdown.button').dropdown({
        allowCategorySelection: true
      })
    },
    progress: function (id, destination) {
      $('#' + id).progress({
        percent: destination
      })
    },
    formatDate: function (date) {
      date = new Date(date)
      return (
        date.getFullYear() +
        ' 年 ' +
        (date.getMonth() + 1) +
        ' 月 ' +
        date.getDate() +
        ' 日 '
      )
    }
  },
  mounted () {
    this.sq = this.gap()
    this.dateNum = parseInt(
      (this.$refs.temporalPlanning.getBoundingClientRect().width - 19.6) / 42.03
    )
    $('.menu .bt1').popup({
      inline: true,
      hoverable: true,
      position: 'bottom left',
      delay: {
        show: 0,
        hide: 0
      }
    })
    $('.staffDropdown').dropdown({
      apiSettings: {
        url: this.$apiUrl + '/staff?name={query}'
      },
      maxSelections: 1
    })
    $('.popupTriger').popup({
      inline: true,
      hoverable: true,
      position: 'right center',
      delay: {
        show: 0,
        hide: 500
      },
      closable: false,
      on: 'click'
    })
  }
}
</script>
<style scoped>
/* .scrollY {
overflow-x: scroll;
overflow-y: hidden;
} */
.link {
  color: rgba(0, 0, 0, 0.7);
}
.link:hover {
  color: black;
  cursor: pointer;
  cursor: hand;
}
.complete {
  width: 500px;
}
.ui.progress:last-child {
  margin: 0px;
}
</style>
