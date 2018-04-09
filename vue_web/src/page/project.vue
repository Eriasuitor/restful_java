<template>
    <div>

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
        <button class="ui labeled icon button right floated" @click="newPhaseEvent">
            <i class="plus icon"></i>新建阶段任务
        </button>
        <h3 class="ui dividing header">{{project.name}}
            <div class="sub header">{{project.description}}</div>
        </h3>
        <div class="scrollY">

            <table class="ui compact red table">
                <thead>
                    <tr>
                        <th class="three wide">阶段</th>
                        <th class="four wide">子任务</th>
                        <th class="three wide">完成度</th>
                        <th class="six wide" id='temporalPlanning' ref='temporalPlanning'>时间规划</th>
                    </tr>
                </thead>
                <tbody>
                    <template v-for="phase in phases">
                        <tr :key="phase.id">
                            <td :rowspan="phase.subtaskList.length + 1">{{phase.name}}{{phase.subtaskList.length}}<br>
                                <div class="ui fade animated button" @click="newSubtaskEvent(phase.id, phase.name)">
                                    <div class="hidden content">子任务</div>
                                    <div class="visible content">
                                        <i class="plus icon"></i>
                                    </div>
                                </div>
                                <div class="ui fade animated button" @click="newSubtaskEvent(phase.id, phase.name)">
                                    <div class="hidden content">编辑</div>
                                    <div class="visible content">
                                        <i class="edit icon"></i>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr v-for="subtask in phase.subtaskList" :key="'sun' + subtask.id">
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
                                <a v-for="(item, index) in dateList" :key="subtask.id + index" :title="'开始于' + format(subtask.startDate) + '，结束于' + format(subtask.endDate)" :class="{ 'ui': true, 'circular': true, 'label': true,'green':subtask.completed === 100, 'red': item >= new Date(subtask.startDate) && (item <= new Date(subtask.endDate) && setRed(subtask.id) || redMap[subtask.id] == undefined && setRed(subtask.id))}">{{item.getMonth() + '.' + item.getDate()}}</a>
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
        <div class="ui modal phase">
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
        <div class="ui modal subtask">
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
    </div>
</template>
<script>
export default {
  data () {
    return {
      startDate: new Date(),
      sq: null,
      iterval: 5,
      userId: 2,
      phases: {},
      phase: {},
      subtask: {},
      sub_phaseId: 0,
      phaseName: 0,
      project: {},
      dateNum: 0,
      dateList: [],
      redMap: {}
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
    endDate: function () {
      return new Date(this.startDate.getTime() + 86400000 * 365)
    }
  },
  methods: {
    setRed: function (key) {
      this.redMap[key] = 1
      return true
    },
    getDateList: function () {
      var gapDays = Math.ceil((
        new Date(this.project.endDate).getTime() -
        new Date(this.project.startDate).getTime()) / 86400000)
      var gapDayBall = Math.ceil(gapDays / (this.dateNum - 1))
      for (var i = 0; i < this.dateNum - 1; i++) {
        this.dateList.push(
          new Date(
            new Date(this.project.startDate).getTime() + gapDayBall * i * 86400000
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
          setTimeout(this.refreshProgressBar, 20)
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
      $('.ui.modal.phase')
        .modal({
          blurring: true,
          centered: false,
          inverted: true
        })
        .modal('setting', 'closable', false)
        .modal('show')
    },
    newSubtaskEvent: function (phaseId, subPhaseName) {
      this.sub_phaseId = phaseId
      this.phaseName = subPhaseName
      $('.ui.modal.subtask')
        .modal({
          blurring: true,
          centered: false,
          inverted: true
        })
        .modal('setting', 'closable', false)
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
    format (datePar) {
      var date = new Date(datePar)
      return date.getFullYear() + '.' + date.getMonth() + '.' + date.getDate()
    }
  },
  mounted () {
    this.sq = this.gap()
    this.dateNum = parseInt(
      (this.$refs.temporalPlanning.getBoundingClientRect().width - 19.6) / 42.03
    )
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
