<template>
    <div>

        <div class="ui animated fade button" tabindex="0">
            <div class="visible content">&nbsp;</div>
            <div class="hidden content">
                <i class="pencil alternate icon"></i>
            </div>
        </div>

        <div>{{endDate.getDate()}}</div>

        <div v-for="(itemm,index) in sq" :key="index">
            {{index}}
            {{item}}
        </div>

    </div>
</template>
<script>
export default {
  data () {
    return {
      startDate: new Date(),
      sq: null
    }
  },
  computed: {
    endDate: function () {
      return new Date(this.startDate.getTime() + 86400000 * 3)
    },
    gap
  },
  methods: {
    gap: function* () {
      var opeDate = this.startDate
      while (this.opeDate < this.endDate) {
        console.log(1)
        yield this.opeDate
        opeDate.setDate(opeDate.getDate() + 1)
      }
    }
  },
  mounted () {
    this.sq = this.gap()
  }
}

</script>
<style scoped>

</style>
