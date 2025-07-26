import hyRequest from '../..'

const getUserInfoReq = (userInfo: any, register: boolean, loginType: string) => {
  return hyRequest.instance({
    url: `/login/${loginType}/${register ? '0' : '1'}`,
    method: 'POST',
    data: userInfo,
  })
}

const getVerifyCodeReq = (phoneNo: string) => {
  return hyRequest.instance({
    url: '/login/verify-code',
    method: 'POST',
    data: phoneNo,
  })
}

const updateUserInfoReq = (userInfo: any) => {
  return hyRequest.instance({
    url: '/user-info',
    method: 'PATCH',
    data: userInfo,
  })
}

export { getUserInfoReq, getVerifyCodeReq, updateUserInfoReq }
