<template>
    <div>
        <div class="ui breadcrumb">
            <a class="section">JIRA</a>
            <div class="divider"> / </div>
            <div class="active section">Projects</div>
        </div>
        <div class="ui search">
            <div class="ui left icon input">
                <input class="prompt" type="text" placeholder="项目搜索" @input="search">
                <i class="search icon"></i>
            </div>
            <button class="ui labeled icon button right floated" @click="newProjectEvent">
                <i class="plus icon"></i>新建项目
            </button>
        </div>
        <h4 class="ui horizontal divider header">
            <i class="briefcase icon"></i> 我管理的
        </h4>
        <div class="ui link cards">
            <div class="card" @click="detail(project.id)" v-for="project in projects" :key="project.id">
                <div class="content">
                    <div class="header">{{project.name}}</div>
                    <div class="meta">
                        {{project.status}}
                    </div>
                    <div class="description ellipsis">{{project.description}}</div>
                </div>
                <div class="extra content">
                    <span class="right floated"> {{(project.timeCost / project.requiredTime * 100).toFixed(2)}}% 完成度 </span>
                    <span>
                        <i class="user icon"></i> {{project.participants}} 参与者 </span>
                </div>
            </div>
        </div>

        <h4 class="ui horizontal divider header">
            <i class="users icon"></i> 我参与的
        </h4>

        <div class="ui modal">
            <i class="close icon black"></i>
            <div class="header">
                新建项目
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>项目名称</label>
                        <input type="text" v-model="project.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="project.description"></textarea>
                    </div>

                    <div class="fields">
                        <div class="eight wide field required" @click="showDropdown(1)">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown manager">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field">
                            <label>
                                代码库地址
                            </label>
                            <input type="url">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                开始时间
                            </label>
                            <input type="date" v-model="project.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="project.endDate">
                        </div>
                    </div>
                    <div class="field required" @click="showDropdown">
                        <label>参与人</label>
                        <div class="fields">
                            <div class="fourteen wide field">
                                <div class="ui fluid multiple search selection dropdown participants">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                            </div>
                            <div class="two wide field">
                                <div class="ui button" @click="empty">
                                    清空
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button" @click="newProject">
                    新建
                </div>
            </div>
            <div v-bind:class="{ui:true, dimmer:true, inverted:true, active:isSaving,}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      projects: [],
      userId: 2,
      project: {},
      isSaving: false
    }
  },
  created () {
    window.localStorage.setItem('12', '123')
    this.refreshProjects()
  },
  methods: {
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
      $('.ui.modal')
        .modal({
          blurring: true,
          centered: false,
          inverted: true
        })
        .modal('setting', 'closable', false)
        .modal('show')
    },
    showDropdown: function (maxSelections) {
      $('.dropdown').dropdown({
        apiSettings: {
          url: this.$apiUrl + '/staff?name={query}'
          //   , onResponse: function (resp) {
          //     for (var i = 0; i < resp.results.length; i++) {
          //       resp.results[i].value = resp.results[i].id
          //     }
          //     return resp
          //   }
        },
        // saveRemoteData: true,
        maxSelections: maxSelections
      })
    },
    empty: function () {
      $('.dropdown.participants').dropdown('clear')
    },
    search: function () {
      $('.ui.search').search({
        apiSettings: {
          url: '//api.github.com/search/repositories?q={query}'
        },
        fields: {
          results: 'items',
          title: 'name',
          url: 'html_url'
        },
        minCharacters: 3
      })
    },
    detail: function (pId) {
      this.$router.push('/projects/' + pId)
    }
  }
}

//   "results": [
//     {
//       "title": "Result Title",
//       "url": "/optional/url/on/click",
//       "image": "optional-image.jpg",
//       "price": "Optional Price",
//       "description": "Optional Description"
//     },
//     {
//       "title": "Result Title",
//       "description": "Result Description"
//     }
//   ],
//   // optional action below results
//   "action": {
//     "url": '/path/to/results',
//     "text": "View all 202 results"
//   }
// }
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
</style>
