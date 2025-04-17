interface DialogueInputDto {
  question: string
  model: number
}

interface Record {
  question: string
  answer: string
}

interface UserInfo {
  jobNo: string
  avatar?: string
  nickName?: string
  gender?: string
  password: string
}

export { DialogueInputDto, Record, UserInfo }
