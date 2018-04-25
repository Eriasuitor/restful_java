<template>
    <div class="ui modal projectModal" id='newProject'>
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
                        <div class="ui fluid multiple search selection dropdown project_manager">
                            <input type="hidden" name="receipt">
                            <i class="dropdown icon"></i>
                            <div class="default text"></div>
                        </div>
                    </div>
                    <div class="eight wide field">
                        <label>
                            代码库地址
                        </label>
                        <input type="url" v-model="project.url">
                    </div>
                </div>
                <div class="fields">
                    <div class="eight wide field required">
                        <label>
                            开始时间
                        </label>
                        <input type="date" :value="startDate" ref='projectStartDate'>{{startDate}}
                    </div>
                    <div class="eight wide field required">
                        <label>
                            结束时间
                        </label>
                        <input type="date" :value="endDate" ref='projectEndDate'>
                    </div>
                </div>
                <div class="field required" @click="showDropdown()">
                    <label>参与人</label>
                    <div class="fields">
                        <div class="fourteen wide field">
                            <div class="ui fluid multiple search selection dropdown project_participants">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="two wide field">
                            <div class="ui button" @click="empty()">
                                清空
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="actions">
            <div class="ui positive right button" @click="save()">
                新建
            </div>
        </div>
        <div v-bind:class="{ui:true, dimmer:true, inverted:true, active:isSaving}">
            <div class="ui text loader ">保存中</div>
        </div>
    </div>
</template>
<script>
export default {
  name: 'ProjectModal',
  props: ['project'],
  data () {
    return {
      isSaving: false
    }
  },
  computed: {
    startDate: function () {
      var date = new Date(this.project.startDate)
      console.log(date.getDay())
      return date.getFullYear() + '-' + (date.getMonth() < 9 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1)) + '-' + (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
    },
    endDate: function () {
      var date = new Date(this.project.endDate)
      return date.getFullYear() + '-' + (date.getMonth() < 9 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1)) + '-' + (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
    }
  },
  methods: {
    // newProjectEvent: function () {
    //   console.log(this.project)
    //   $('.newProject')
    //     .modal({
    //       centered: true,
    //       blurring: true,
    //       inverted: false,
    //       closable: false,
    //       context: 'body2'
    //     })
    //     .modal('show')
    // },
    save: function () {
      this.$emit('save', this.project)
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
    }
  }
}
</script>
