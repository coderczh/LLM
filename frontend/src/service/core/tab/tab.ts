import hyRequest from '../..'

const getUserInfoReq = (userInfo: any) => {
  return hyRequest.instance({
    url: '/login/account',
    method: 'POST',
    data: userInfo,
  })
}

export { getUserInfoReq }
