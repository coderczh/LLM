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
    async getUserInfo(userInfo: any, register: boolean) {
      const res = await getUserInfoReq(userInfo, register)
      if (res.data.code === SUCCESS_CODE) {
        localCache.setCache('userInfo', res.data.data)
      } else {
        localCache.removeCache('userInfo')
        ElMessage.closeAll()
        ElMessage({
          type: 'error',
          message: res.data.message,
        })
      }
    },
  },
})

export { loginStore }
