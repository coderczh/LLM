import type { UserInfo } from '@/assets/common/common'
import { getUserInfo } from '@/service/aside/aside'
import { defineStore } from 'pinia'

const loginStore = defineStore('login', {
  state: (): UserInfo => ({
    jobNo: '',
    avatar: '',
    nickName: '',
    gender: '',
    password: '',
  }),
  actions: {
    getUserInfo: async (userInfo: UserInfo) => {
      const res = await getUserInfo(userInfo)
      console.log(res)
    },
  },
})

export { loginStore }
