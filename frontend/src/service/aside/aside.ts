import type { UserInfo } from '@/assets/common/common'
import hyRequest from '..'

const getUserInfo = (userInfo: UserInfo) => {
  return hyRequest.instance({
    url: '/login/user-info',
    method: 'PUT',
    data: userInfo,
  })
}

export { getUserInfo }
