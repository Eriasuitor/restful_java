<template>
    <div>
        <div class="ui breadcrumb">
            <a class="section">Projects</a>
            <div class="divider"> / </div>
            <div class="active section">Paper Towels</div>
        </div>
        <div class="ui search">
            <div class="ui left icon input">
                <input class="prompt" type="text" placeholder="项目搜索" @input="search">
                <i class="search icon"></i>
            </div>
            <button class="ui labeled icon button right floated" @click="newProject">
                <i class="plus icon"></i>新建项目</button>
        </div>

        <h4 class="ui horizontal divider header">
            <i class="briefcase icon"></i> 我管理的 </h4>

        <div class="ui link cards">
            <div class="card">
                <div class="content">
                    <div class="header">项目开发管理系统</div>
                    <div class="meta">
                        好友
                    </div>
                    <div class="description ellipsis">Mattheasfw is anasasasdas asdas fsdf dasd d asdas interior designer living in New York. </div>
                </div>
                <div class="extra content">
                    <span class="right floated">100%完成度 </span>
                    <span>
                        <i class="user icon"></i> 75 参与者 </span>
                </div>
            </div>
        </div>

        <h4 class="ui horizontal divider header">
            <i class="users icon"></i> 我参与的 </h4>

        project

        <div class="ui modal">
            <i class="close icon black"></i>
            <div class="header">
                新建项目
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field">
                        <label>项目名称</label>
                        <input type="text">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800"></textarea>
                    </div>

                    <div class="fields">
                        <div class="eight wide field" @click="showDropdown(1)">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown">
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
                        <div class="eight wide field">
                            <label>
                                开始时间
                            </label>
                            <input type="datetime-local">
                        </div>
                        <div class="eight wide field">
                            <label>
                                结束时间
                            </label>
                            <input type="datetime-local">
                        </div>
                    </div>
                    <div class="field" @click="showDropdown">
                        <label>参与人</label>
                        <div class="fields">
                            <div class="fourteen wide field">
                                <div class="ui fluid multiple search selection dropdown">
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
                <div class="ui positive right button">
                    新建
                </div>
            </div>
        </div>

    </div>
</template>
<script>
export default {
  created () {
    console.log(this.$route)
  },
  methods: {
    newProject: function () {
      console.log('ADD')
      $('.ui.modal')
        .modal({
          blurring: true,
          centered: false,
          inverted: true
        })
        .modal('setting', 'closable', true)
        .modal('show')
    },
    showDropdown: function (maxSelections) {
      $('.dropdown').dropdown({
        apiSettings: {
          url: '//api.semantic-ui.com/tags/{query}'
        },
        maxSelections: maxSelections
      })
    },
    empty: function () {
      $('.dropdown').dropdown('clear')
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
