<template>
  <div>
    <div v-for="_project in projects">
    <h3 class="ui dividing header">{{_project.name}}
    </h3>
    <div class="ui small statistics">
      <div class="statistic">
        <div class="value">
          {{_project.completed}}
        </div>
        <div class="label">完成(%)</div>
      </div>
      <div class="statistic">
        <div class="value">
          {{_project.timeCost}}
        </div>
        <div class="label">時間花費(元)</div>
      </div>
      <div class="statistic">
        <div class="value">
          {{_project.economicCost}}
        </div>
        <div class="label">經濟消耗(小时)</div>
      </div>
      <div class="statistic">
        <div class="value">
          {{_project.participants}}
        </div>
        <div class="label">參與者</div>
      </div>
      <div class="statistic">
        <div class="value">
          {{_project.requiredTime}}
        </div>
        <div class="label">预计用时(小时)</div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        userId: 2,
        projects: []
      }
    },
    created() {
      this.getProjects()
    },
    methods: {
      getProjects: function() {
        this.$api.get(
          this.$apiUrl + '/projects?staffid=' + this.userId,
          null,
          data => {
            this.projects = data.projectNames
          }
        )
      },
    }
  }
</script>
