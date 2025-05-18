import { localCache } from '@/assets/common/cache'
import { SUCCESS_CODE } from '@/assets/common/constant'
import { getUserInfoReq } from '@/service/core/tab/tab'
import { ElMessage } from 'element-plus'
import { defineStore } from 'pinia'

const loginStore = defineStore('login', {
  state: () => ({
    id: '',
    nickName: '',
    avatar: '',
    gender: '',
    accountNo: '',
    password: '',
    phoneNo: '',
    email: '',
  }),
  actions: {
    async getUserInfo(userInfo: any) {
      const res = await getUserInfoReq(userInfo)
      if (res.data.code === SUCCESS_CODE) {
        localCache.setCache('userInfo', res.data.data)
      } else {
        ElMessage({
          type: 'error',
          message: res.data.message,
        })
      }
    },
  },
})

export { loginStore }
