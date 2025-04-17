import { localCache } from '@/assets/common/cache'
import type { UserInfo } from '@/assets/common/common'
import { SUCCESS_CODE, USER_INFO } from '@/assets/common/constant'
import { getUserInfoReq } from '@/service/aside/aside'
import { ElMessage } from 'element-plus'
import { defineStore } from 'pinia'

const useLoginStore = defineStore('login', {
  state: (): UserInfo => ({
    jobNo: '',
    avatar: '',
    nickName: '',
    gender: '',
    password: '',
  }),
  actions: {
    async getUserInfo(userInfo: UserInfo) {
      const res = await getUserInfoReq(userInfo)
      if (res.data.code === SUCCESS_CODE) {
        this.$patch(res.data.data)
        localCache.setCache(USER_INFO, res.data.data)
      } else {
        ElMessage({
          type: 'error',
          message: res.data.message,
        })
      }
    },
  },
})

export { useLoginStore }
