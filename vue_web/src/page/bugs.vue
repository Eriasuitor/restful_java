<template>
    <div2>

<div class="ui segment">
  <a class="ui blue ribbon label"> 尚未解决 </a>
  <button class="ui labeled icon button right floated" @click="submitBugEvent">
     <i class="plus icon"></i>提交新 Bug
</button>
  <div class="ui clearing divider"></div>
  <div class="ui cards">
        <div class="card">
          <div class="content">
            <span class="ui right corner label">
        <i class="warning red icon"></i>
      </span>
            <div class="header clickable">当点击提交按钮时其并未正确显示应有画面</div>
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
            <div class="header">当点击提交按钮时其并未正确显示应有画面 Hess
                <div class="note">来自项目：超级工程建设
            </div>
              <div class="note">来自子任务：创建提交按钮已提交问卷
            </div>

            </div>
            <div class="description">我们点击此处时，发现并没有正确的显示。 </div>
            <div class="ui feed">
  <div class="event">
    <div class="label">
      <img src="/images/avatar/small/laura.jpg">
    </div>
    <div class="content">
      <div class="summary">丫丫</div>
      <div class="extra text">测试显示并非我</div>
    </div>
  </div>
</div>
          </div>
          <div class="ui bottom attached buttons">
  <div class="ui button" ><i class="add icon"></i> Close </div>
  <div class="ui button green"><i class="add icon"></i> Resolve </div>
</div>
        </div>
      </div>
</div>

<div class="ui segment">
  <a class="ui green ribbon label"> 现已解决 </a>
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
      
      <div class="ui modal newBug" id='newBug'>
            <i class="close icon black"></i>
            <div class="header">
                提交新 Bug
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                所属项目
                            </label>
                            <div class="ui fluid multiple search selection dropdown projectDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field required">
                            <label>
                                所属子任务
                            </label>
                            <div class="ui fluid multiple search selection dropdown subtaskDropdown">
                                <input type="hidden" name="subtask">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                            <!-- <div class="ui fluid multiple search selection dropdown subtaskDropdown" @click="subtaskDropdown()">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div> -->
                        </div>
                        <div class="eight wide field required">
                            <label>
                                名称
                            </label>
                            <input type="date" v-model="log.endDate">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="four wide field">
                            <label>
                                描述
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.timeCost">
                                <div class="ui basic label">小时 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                重要程度
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.economicCost">
                                <div class="ui basic label">元 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                起源
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.completed">
                                <div class="ui basic label">% </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                来源
                            </label>
                            <div class="ui fluid multiple search selection dropdown log_assign staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="four wide field">
                            <label>
                                严重程度
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.timeCost">
                                <div class="ui basic label">小时 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                优先级
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.economicCost">
                                <div class="ui basic label">元 </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                分配给
                            </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.completed">
                                <div class="ui basic label">% </div>
                            </div>
                        </div>
                        <div class="four wide field">
                            <label>
                                @
                            </label>
                            <div class="ui fluid multiple search selection dropdown log_assign staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
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
      subtasks: [],
      bug: {},
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
    submitBugEvent: function () {
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
    subtaskDropdown: function() {
      console.log(this.$apiUrl + '/projects/' + $('.dropdown.projectDropdown').dropdown('get value') + '/phases')
     
    // $('.subtaskDropdown').dropdown()
    }
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
    $('.projectDropdown').dropdown({
      apiSettings: {
          onResponse: resp => {
            resp.results = resp.projectNames;
            $.each(resp.projectNames, function (index, item) {
              item.value = item.id
            })
            return resp
          },
          url: this.$apiUrl + '/projects?q={query}'
        },
        maxSelections: 1
    })
    // $('.subtaskDropdown').dropdown({
    //   apiSettings: {
    //       onResponse: resp => {
    //         resp.results = resp.projectNames;
    //         $.each(resp.projectNames, function (index, item) {
    //           item.value = item.id
    //         })
    //         return resp
    //       },
    //       url: this.$apiUrl + '/projects?q={query}'
    //     },
    //     maxSelections: 1
    // })
     $('.subtaskDropdown').dropdown({
      apiSettings: {
          onResponse: resp => {
            let subtasks = []
            resp.phaseList.forEach(element => {
              element.subtaskList.forEach(item => subtasks.push({value: item.id, name : item.name}))
            })
            resp.results = subtasks
            return resp
          },
          url: this.$apiUrl + '/projects/1/phases?q=11{query}'
        },
        maxSelections: 1
        //  onResponse: resp => {
        //     resp.results = resp.projectNames;
        //     $.each(resp.projectNames, function (index, item) {
        //       item.value = item.id
        //     })
        //     return resp
        //   },
        //   url: this.$apiUrl + '/projects?q={query}'
        // },
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
