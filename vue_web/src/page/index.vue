<template>
  <div><div class="ui cards">
        <div class="card">
          <div class="content">
            <span class="ui right corner label">
        <i class="warning red icon"></i>
      </span>
            <div class="header clickable">完成第一个子任务</div>
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
            <div class="header">多大事愛仕達水電第三方費艾克 Hess</div>
            <!-- <div class="description">Jenny是一名在该新学校学习媒体管理的在校生。 </div> -->
          <div class="ui small statistics">
  <div class="statistic">
    <div class="value">
      22
    </div>
    <div class="label">Faves </div>
  </div>
  <div class="statistic">
    <div class="value">
      310
    </div>
    <div class="label">视图 </div>
  </div>
  <div class="statistic">
    <div class="value">
      22
    </div>
    <div class="label">会员 </div>
  </div>
  <div class="statistic">
    <div class="value">
      22
    </div>
    <div class="label">会员 </div>
  </div>
</div>
          </div>
          <div class="ui bottom attached button"><i class="add icon"></i> Add Friend </div>
        </div>
      </div>
</div></div>
</template>
<script>
export default {
  created () {
    console.log(this.$route)
  }
}
</script>
