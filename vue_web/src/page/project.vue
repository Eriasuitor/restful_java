<template>
    <div>

    <div class="ui breadcrumb">
      <a class="section">Projects</a>
      <div class="divider"> / </div>
      <div class="active section">Paper Towels</div>
    </div>

        <div class="ui dropdown button" @click="intervalSet()">
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
        </div>
        <button class="ui labeled icon button right floated" @click="newPhases">
            <i class="plus icon"></i>新建阶段任务
        </button>

<div class="scrollY">
<table class="ui collapsing celled structured small compact single line table ">
  <thead>
    <tr>
      <th rowspan="2">阶段</th>
      <th rowspan="2" class="subtask">子任务</th>
      <th rowspan="2" v-for="(item,index) in dates()" :key="index">

            <div class="ui mini horizontal statistic red" v-show="item.getDate() / iterval < 1 || index === 0">
              <div class="value">
                {{item.getMonth()}}-
              </div>
            </div>
            <span ></span>
            {{item.getDate()}}
        </th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="3">第一阶段<br>
          <div class="ui fade animated button" @click="newSubtask">
            <div class="hidden content">子任务</div>
            <div class="visible content">
              <i class="plus icon"></i>
            </div>
          </div>
      </td>
      <td>需求文档撰写</td>

      <td v-for="(item,index) in dates()" :key="index" class="center aligned">
        <i class="large green checkmark icon"></i>
      </td>
    </tr>
    <tr>
      <td>需求文档的讨论与定稿</td>
      <td class="right aligned">12</td>
      <td></td>
      <td class="center aligned">
        <i class="large minus icon"></i>
      </td>
      <td></td>
    </tr>
    <tr>
      <td>去死</td>
      <td class="right aligned">21</td>
      <td class="center aligned">
        <i class="large green checkmark icon"></i>
      </td>
      <td></td>
      <td></td>
    </tr>
  </tbody>
</table>
      456{{endDate}}
        <div class="ui animated fade button" tabindex="0">
            <div class="visible content">&nbsp;</div>
            <div class="hidden content">
                <i class="pencil alternate icon"></i>
            </div>
        </div>

        <div>{{endDate.getDate()}}</div>

        <!-- <div v-for="(item,index) in dates()" :key="index">
          <div class="ui mini label"></div>
            <span v-if="item.getDate() === 1 || index === 0">{{item.getMonth()}}</span>-
            {{item.getDate()}}
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
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button">
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
                        <div class="eight wide field" @click="showDropdown(1)">
                            <label>
                                分配于
                            </label>
                            <div class="ui fluid multiple search selection dropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field" @click="showDropdown(1)">
                            <label>
                                阶段
                            </label>
                            <div class="ui fluid multiple search selection dropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field" @click="showDropdown(1)">
                            <label>
                                预计用时
                            </label>
                            <div class="ui right labeled input">
                              <input type="number" step="0.5" min="0">
                              <div class="ui basic label">小时 </div>
                            </div>
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
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right button">
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
      iterval: 5
    }
  },
  computed: {
    endDate: function () {
      return new Date(this.startDate.getTime() + 86400000 * 365)
    }
  },
  methods: {
    gap: function* () {
      var opeDate = this.startDate
      while (this.opeDate < this.endDate) {
        console.log(1)
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
    newPhases: function () {
      $('.ui.modal.phase')
        .modal({
          blurring: true,
          centered: false,
          inverted: true
        })
        .modal('setting', 'closable', true)
        .modal('show')
    },
    newSubtask: function () {
      $('.ui.modal.subtask')
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
    intervalSet: function () {
      console.log(1)
      $('.ui.dropdown.button').dropdown({
        allowCategorySelection: true
      })
    }
  },
  mounted () {
    this.sq = this.gap()
  }
}

</script>
<style scoped>
.scrollY{
  overflow-x:scroll;
  overflow-y:hidden;
}
</style>
