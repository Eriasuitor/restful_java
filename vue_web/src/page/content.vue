<template>
  <div>Content</div>
</template>
<script>
export default {
  created(){
      console.log(this.$route)
      console.log(this.$route.params.id)
      this.$router.push('/')
  }
}
</script>
