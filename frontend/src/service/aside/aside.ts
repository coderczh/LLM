import type { UserInfo } from '@/assets/common/common'
import hyRequest from '..'

const getUserInfoReq = (userInfo: UserInfo) => {
  return hyRequest.instance({
    url: '/login/user-info',
    method: 'POST',
    data: userInfo,
  })
}

export { getUserInfoReq }
